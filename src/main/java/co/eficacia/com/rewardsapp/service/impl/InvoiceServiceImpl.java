package co.eficacia.com.rewardsapp.service.impl;

import co.eficacia.com.rewardsapp.constant.InvoiceErrorCode;
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

    private final PromotionService promotionService;

    private final UserMapper userMapper;

    private UserService userService;

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
    public Invoice approvedTransaction(Invoice invoice) {

        invoice.setState(invoice.APPROVED);

        List<Promotion> listPromotion = invoice.getPromotionList();

        for (Promotion promotion : listPromotion) {

            if (promotion.getQuantityPromotions() > 0 ) {

                promotion.setQuantityPromotions(promotion.getQuantityPromotions() - 1);

                AccumulatedTransaction transaction = new AccumulatedTransaction();

                transaction.setInvoice(invoice);

                transaction.setSource(transaction.PROMOTION);

                transaction.setAccumulatedPoints(promotion.getOfferedPoints());

                transaction.setProductQuantity(1);

                transaction.setUser(invoice.getUser());

                transaction.setTimestamp(invoice.getTimestamp());

                accumulatedTransactionService.createAccumulatedTransaction(transaction);

                promotionService.updatePromotion(promotion);
            }
        }

        invoiceRepository.save(invoice);

        return invoice;
    }

    @Override
    public Invoice noApprovedTransactions(Invoice invoice) {

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

                    user.setPendingPoint(user.getPendingPoint() + promotion.getOfferedPoints());

                }
            }
        }

        userService.updateUser(user);

        return user.getPendingPoint();
    }
}
