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

    private AccumulatedTransactionRepository accumulatedTransactionsRepository;

    @BeforeEach
    public void init() {
        invoiceRepository = mock(InvoiceRepository.class);
        invoiceService = new InvoiceServiceImpl(invoiceRepository);
        accumulatedTransactionsRepository = mock(AccumulatedTransactionRepository.class);

    }

    // Aprovar transacion del usuario
    @Test
    public void testapproveTransaction() {
        Promotion promotion = new Promotion();
        List<Promotion> listPromotion = new ArrayList<>();
        listPromotion.add(promotion);
        PromotionRepository promotionRepository = mock(PromotionRepository.class);
        when(promotionRepository.save(any())).thenReturn(promotion);
        Invoice invoice = new Invoice();
        invoice.setPromotionList(listPromotion);
        invoice.setState(invoice.APPROVED);
        InvoiceRepository invoiceRepository = mock(InvoiceRepository.class);
        when(invoiceRepository.save(any())).thenReturn(invoice);
        AccumulatedTransaction accumulatedTransaction = new AccumulatedTransaction();
        when(accumulatedTransactionsRepository.save(any())).thenReturn(accumulatedTransaction);
        assertEquals("Aprovado", invoiceService.approveTransaction(invoice).getState());
    }

    // No aprovar transacion del usuario
    @Test
    public void testnoApproveTransaction() {

    }

    @Test
    public void testcurrentPendingPointsUser() {

        Promotion promotion1 = new Promotion();
        Promotion promotion2 = new Promotion();
        Promotion promotion3 = new Promotion();
        Promotion promotion4 = new Promotion();
        List<Promotion> listPromotion1 = new ArrayList<>();
        List<Promotion> listPromotion2 = new ArrayList<>();
        promotion1.setOfferedPoints(100);
        promotion2.setOfferedPoints(100);
        promotion3.setOfferedPoints(100);
        promotion4.setOfferedPoints(100);
        listPromotion1.add(promotion1);
        listPromotion1.add(promotion2);
        listPromotion2.add(promotion3);
        listPromotion2.add(promotion4);
        Invoice invoice1 = new Invoice();
        Invoice invoice2 = new Invoice();
        invoice1.setState(invoice1.PENDING);
        invoice2.setState(invoice2.PENDING);
        invoice1.setPromotionList(listPromotion1);
        invoice2.setPromotionList(listPromotion2);
        PromotionRepository promotionRepository = mock(PromotionRepository.class);
        //when(promotionRepository.save(any())).thenReturn(promotion1);
        //InvoiceRepository invoiceRepository = mock(InvoiceRepository.class);
        //when(invoiceRepository.save(any())).thenReturn(invoice1);

        //assertEquals(400, invoiceService.currentPendingPointsUser());

    }
    
}
