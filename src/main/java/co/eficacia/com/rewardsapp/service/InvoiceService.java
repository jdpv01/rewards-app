package co.eficacia.com.rewardsapp.service;

import co.eficacia.com.rewardsapp.persistance.model.Invoice;
import co.eficacia.com.rewardsapp.web.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface InvoiceService {

    Invoice createInvoice(Invoice invoice);

    Invoice getInvoice(UUID id);

    List<Invoice> getPendingInvoices();

    Invoice updateInvoice(Invoice invoice);

    boolean deleteInvoice(UUID id);

    Invoice approveTransaction(Invoice invoice);

    Invoice noApproveTransactions(Invoice invoice);

    Integer currentPendingPointsUser(UserDTO userDTO);
}
