package co.eficacia.com.rewardsapp.service;

import co.eficacia.com.rewardsapp.persistance.model.Invoice;
import co.eficacia.com.rewardsapp.persistance.model.User;
import co.eficacia.com.rewardsapp.web.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface InvoiceService {

    Invoice createInvoice(Invoice invoice);

    Invoice getInvoice(UUID id);

    List<Invoice> getPendingInvoices();

    Invoice updateInvoice(Invoice invoice);

    boolean deleteInvoice(UUID id);

    Invoice approvedTransaction(Invoice invoice);

    Invoice noApprovedTransactions(Invoice invoice);

    Integer currentPendingPointsUser(UserDTO userDTO);
}
