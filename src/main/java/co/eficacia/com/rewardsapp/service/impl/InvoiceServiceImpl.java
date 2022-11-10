package co.eficacia.com.rewardsapp.service.impl;

import co.eficacia.com.rewardsapp.constant.InvoiceErrorCode;
import co.eficacia.com.rewardsapp.error.ObjectError;
import co.eficacia.com.rewardsapp.error.exception.GlobalException;
import co.eficacia.com.rewardsapp.model.AccumulatedTransaction;
import co.eficacia.com.rewardsapp.model.Invoice;
import co.eficacia.com.rewardsapp.model.InvoicePromotion;
import co.eficacia.com.rewardsapp.model.Promotion;
import co.eficacia.com.rewardsapp.repository.InvoiceRepository;
import co.eficacia.com.rewardsapp.service.AccumulatedTransactionService;
import co.eficacia.com.rewardsapp.service.InvoiceService;
import co.eficacia.com.rewardsapp.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    private final AccumulatedTransactionService accumulatedTransactionService;

    private final PromotionService  promotionService;

    @Override
    public Invoice createInvoice(Invoice invoice) {
        invoice.setState(invoice.PENDING);
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice getInvoice(UUID id) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        if (optionalInvoice.isPresent())
            return optionalInvoice.get();
        throw new GlobalException(HttpStatus.NOT_FOUND, new ObjectError(InvoiceErrorCode.CODE_01, InvoiceErrorCode.CODE_01.getMessage()));
    }

    @Override
    public List<Invoice> getPendingInvoices() {
        List<Invoice> invoices = StreamSupport.stream(invoiceRepository.findAll().spliterator(), false).collect(Collectors.toList());
        List<Invoice> aux = new ArrayList<>();
        for (Invoice invoice : invoices) {
            if (invoice.getState().equals(invoice.PENDING)) {
                aux.add(invoice);
            }
        }
        return aux;
    }

    @Override
    public Invoice updateInvoice(Invoice Invoice) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(Invoice.getId());
        if (optionalInvoice.isPresent())
            return invoiceRepository.save(Invoice);
        throw new GlobalException(HttpStatus.NOT_FOUND, new ObjectError(InvoiceErrorCode.CODE_01, InvoiceErrorCode.CODE_01.getMessage()));
    }

    @Override
    public boolean deleteInvoice(UUID id) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        if (optionalInvoice.isPresent()) {
            invoiceRepository.delete(optionalInvoice.get());
            return true;
        }
        throw new GlobalException(HttpStatus.NOT_FOUND, new ObjectError(InvoiceErrorCode.CODE_01, InvoiceErrorCode.CODE_01.getMessage()));
    }

    @Override
    public void approvedTransactions(Invoice invoice) {

        invoice.setState(invoice.APPROVED);

        List<InvoicePromotion> listPromotion = invoice.getInvoicePromotionList();

        for(InvoicePromotion invoicePromotion : listPromotion) {

            Promotion promotion = invoicePromotion.getPromotion();

            if (invoicePromotion.getQuantity() <= promotion.getQuantityPromotions()){

                promotion.setQuantityPromotions(promotion.getQuantityPromotions() - invoicePromotion.getQuantity());

                AccumulatedTransaction transaction = new AccumulatedTransaction();

                transaction.setInvoice(invoice);

                transaction.setSource(transaction.PROMOTION);

                transaction.setAccumulatedPoints(promotion.getOfferedPoints());

                transaction.setProductQuantity(invoicePromotion.getQuantity());

                transaction.setUser(invoice.getUser());

                transaction.setTimestamp(invoice.getTimestamp());

                accumulatedTransactionService.createAccumulatedTransaction(transaction);

                promotionService.updatePromotion(promotion);
            }

        }

        invoiceRepository.save(invoice);

    }

    @Override
    public void noApprovedTransactions(Invoice invoice) {

        invoice.setState(invoice.NOT_APPROVED);

        invoiceRepository.save(invoice);

    }

}
