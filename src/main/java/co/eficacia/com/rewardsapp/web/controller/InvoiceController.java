package co.eficacia.com.rewardsapp.web.controller;

import co.eficacia.com.rewardsapp.mapper.InvoiceMapper;
import co.eficacia.com.rewardsapp.service.InvoiceService;
import co.eficacia.com.rewardsapp.web.api.InvoiceAPI;
import co.eficacia.com.rewardsapp.web.dto.InvoiceDTO;
import co.eficacia.com.rewardsapp.web.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class InvoiceController implements InvoiceAPI {

    private final InvoiceService invoiceService;

    private final InvoiceMapper invoiceMapper;

    @Override
    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {
        return invoiceMapper.fromInvoice(invoiceService.createInvoice(invoiceMapper.fromInvoiceDTO(invoiceDTO)));
    }

    @Override
    public InvoiceDTO getInvoice(UUID id) {
        return invoiceMapper.fromInvoice(invoiceService.getInvoice(id));
    }

    @Override
    public List<InvoiceDTO> getPendingInvoices() {
        return invoiceService.getPendingInvoices().stream().map(invoiceMapper::fromInvoice).collect(Collectors.toList());
    }

    @Override
    public InvoiceDTO updateInvoice(InvoiceDTO invoiceDTO) {
        return invoiceMapper.fromInvoice(invoiceService.updateInvoice(invoiceMapper.fromInvoiceDTO(invoiceDTO)));
    }

    @Override
    public boolean deleteInvoice(UUID id) {
        return invoiceService.deleteInvoice(id);
    }

    @Override
    public InvoiceDTO approvedTransaction(InvoiceDTO invoiceDTO) {
        return invoiceMapper.fromInvoice(invoiceService.approveTransaction(invoiceMapper.fromInvoiceDTO(invoiceDTO)));
    }

    @Override
    public InvoiceDTO noApprovedTransactions(InvoiceDTO invoiceDTO) {
        return invoiceMapper.fromInvoice(invoiceService.noApproveTransactions(invoiceMapper.fromInvoiceDTO(invoiceDTO)));
    }

    @Override
    public Integer currentPendingPointsUser(UserDTO userDTO) {
        return invoiceService.currentPendingPointsUser(userDTO);
    }



}
