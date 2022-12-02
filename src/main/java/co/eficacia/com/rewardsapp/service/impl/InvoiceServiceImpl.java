package co.eficacia.com.rewardsapp.service.impl;

import co.eficacia.com.rewardsapp.constant.InvoiceErrorCode;
import co.eficacia.com.rewardsapp.mapper.InvoiceMapper;
import co.eficacia.com.rewardsapp.mapper.UserMapper;
import co.eficacia.com.rewardsapp.persistance.model.*;
import co.eficacia.com.rewardsapp.web.dto.UserDTO;
import co.eficacia.com.rewardsapp.web.error.ObjectError;
import co.eficacia.com.rewardsapp.web.error.exception.GlobalException;
import co.eficacia.com.rewardsapp.persistance.repository.InvoiceRepository;
import co.eficacia.com.rewardsapp.service.AccumulatedTransactionService;
import co.eficacia.com.rewardsapp.service.InvoiceService;
import co.eficacia.com.rewardsapp.service.PromotionService;
import co.eficacia.com.rewardsapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    private AccumulatedTransactionService accumulatedTransactionService;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private UserService userDetailsService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private InvoiceMapper invoiceMapper;

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

    @Override                       //List<Promotion
    public Invoice approveTransaction(Invoice invoice) {
        List<Promotion> listPromotion = invoice.getPromotionList();
        for (Promotion promotion : listPromotion) {
            promotion.setAvailableQuantity(promotion.getAvailableQuantity() - 1);
            AccumulatedTransaction transaction = new AccumulatedTransaction();
            transaction = updateDataTransaction(transaction, invoice, promotion);
            accumulatedTransactionService.createAccumulatedTransaction(transaction);
            promotionService.updatePromotion(promotion);
        }
        invoice.setState(invoice.APPROVED);
        invoiceRepository.save(invoice);
        return invoice;
    }

    public AccumulatedTransaction updateDataTransaction(AccumulatedTransaction transaction,Invoice invoice, Promotion promotion) {
        transaction.setInvoice(invoice);
        transaction.setSource(transaction.PROMOTION);
        transaction.setAccumulatedPoints(promotion.getOfferedPoints());
        transaction.setProductQuantity(1);
        transaction.setUser(invoice.getUser());
        transaction.setTimestamp(invoice.getTimestamp());
        return transaction;
    }

    @Override
    public Invoice noApproveTransactions(Invoice invoice) {
        invoice.setState(invoice.NOT_APPROVED);
        invoiceRepository.save(invoice);
        return invoice;
    }

    @Override
    public Integer currentPendingPointsUser(UserDTO userDTO) {

        User user = userMapper.fromUserDTO(userDTO);

        List<Invoice> invoices = StreamSupport.stream(invoiceRepository.findAll().spliterator(), false).collect(Collectors.toList());

        for (Invoice invoice : invoices) {

            if (invoice.getState().equals(invoice.PENDING) && invoice.getUser().getId() == user.getId()) {

                List<Promotion> listPromotion = invoice.getPromotionList();

                for (Promotion promotion : listPromotion) {

                    user.setPendingPoints(user.getPendingPoints() + promotion.getOfferedPoints());

                }
            }
        }

        userDetailsService.updateUser(user);

        return user.getPendingPoints();
    }
}
