package co.eficacia.com.rewardsapp.service;

import co.eficacia.com.rewardsapp.mapper.InvoiceMapper;
import co.eficacia.com.rewardsapp.persistance.model.AccumulatedTransaction;
import co.eficacia.com.rewardsapp.persistance.model.Invoice;
import co.eficacia.com.rewardsapp.persistance.model.Promotion;
import co.eficacia.com.rewardsapp.persistance.model.Publication;
import co.eficacia.com.rewardsapp.persistance.repository.AccumulatedTransactionRepository;
import co.eficacia.com.rewardsapp.persistance.repository.InvoiceRepository;
import co.eficacia.com.rewardsapp.persistance.repository.PromotionRepository;
import co.eficacia.com.rewardsapp.service.impl.InvoiceServiceImpl;
import co.eficacia.com.rewardsapp.web.dto.AccumulatedTransactionDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InvoiceServiceTest {

    private InvoiceService invoiceService;

    private InvoiceRepository invoiceRepository;

    private InvoiceMapper invoiceMapper;

    private PromotionRepository promotionRepository;

    private AccumulatedTransactionRepository accumulatedTransactionsRepository;

    @BeforeEach
    public void init() {
        invoiceRepository = mock(InvoiceRepository.class);
        invoiceService = new InvoiceServiceImpl(invoiceRepository);
        promotionRepository = mock(PromotionRepository.class);
        accumulatedTransactionsRepository = mock(AccumulatedTransactionRepository.class);

    }

    // Aprovar transacion del usuario
    @Test
    public void testapproveTransaction() {
        Promotion promotion = new Promotion();
        List<Promotion> listPromotion = new ArrayList<>();
        listPromotion.add(promotion);
        Invoice invoice = new Invoice();
        invoice.setPromotionList(listPromotion);
        invoice.setState(invoice.APPROVED);
        when(invoiceRepository.save(any())).thenReturn(invoice);
        when(promotionRepository.save(any())).thenReturn(promotion);
        AccumulatedTransaction accumulatedTransaction = new AccumulatedTransaction();
        when(accumulatedTransactionsRepository.save(any())).thenReturn(accumulatedTransaction);
        assertEquals(invoice.getState(), invoiceService.approveTransaction(invoice).getState());
    }

    // No aprovar transacion del usuario
    @Test
    public void testnoApproveTransaction() {

    }

    @Test
    public void testcurrentPendingPointsUser() {

    }
    
}
