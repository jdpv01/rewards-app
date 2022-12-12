package co.eficacia.com.rewardsapp.web.controller;

import co.eficacia.com.rewardsapp.mapper.InvoiceMapper;
import co.eficacia.com.rewardsapp.persistance.model.Invoice;
import co.eficacia.com.rewardsapp.persistance.model.Promotion;
import co.eficacia.com.rewardsapp.service.InvoiceService;
import co.eficacia.com.rewardsapp.web.api.InvoiceAPI;
import co.eficacia.com.rewardsapp.web.dto.InvoiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class InvoiceController implements InvoiceAPI {

    private final InvoiceService invoiceService;

    private final InvoiceMapper invoiceMapper;

    @Override
    public InvoiceDTO createInvoice(MultipartFile invoiceImage, UUID userId, UUID storeId, List<UUID> promotionIdList) {
        Invoice invoice = invoiceService.createInvoice(invoiceImage, userId, storeId, promotionIdList);
        return getInvoiceDTO(invoice);
    }

    @Override
    public InvoiceDTO processInvoice(UUID invoiceId, boolean decision) {
        Invoice invoice = invoiceService.processInvoice(invoiceId, decision);
        return getInvoiceDTO(invoice);
    }

    private InvoiceDTO getInvoiceDTO(Invoice invoice) {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setId(invoice.getId());
        invoiceDTO.setStoreName(invoice.getStore().getName());
        invoiceDTO.setState(invoice.getState());
        invoiceDTO.setPendingPoints(invoice.getPendingPoints());
        List<String> promotionNameList =
                invoice.getPromotionList().stream().map(Promotion::getName).collect(Collectors.toList());
        invoiceDTO.setPromotionNameList(promotionNameList);
        invoiceDTO.setTimestamp(invoice.getTimestamp());
        return invoiceDTO;
    }

    @Override
    public InvoiceDTO getInvoice(UUID id) {
        return invoiceMapper.fromInvoice(invoiceService.getInvoice(id));
    }

    @Override
    public List<InvoiceDTO> getInvoices() {
        return invoiceService.getInvoices().stream().map(invoiceMapper::fromInvoice).collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDTO> getUserInvoices(UUID userId) {
        List<InvoiceDTO> invoiceDTOList = new ArrayList<>();
        invoiceService.getUserInvoices(userId).forEach(invoice -> invoiceDTOList.add(getInvoiceDTO(invoice)));
        return invoiceDTOList;
    }

    @Override
    public InvoiceDTO updateInvoice(UUID id, InvoiceDTO invoiceDTO) {
        return invoiceMapper.fromInvoice(invoiceService.updateInvoice(invoiceMapper.fromInvoiceDTO(id, invoiceDTO)));
    }

    @Override
    public boolean deleteInvoice(UUID id) {
        return invoiceService.deleteInvoice(id);
    }
}
