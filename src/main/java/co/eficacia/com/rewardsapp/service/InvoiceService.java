package co.eficacia.com.rewardsapp.service;

import co.eficacia.com.rewardsapp.model.Invoice;

import java.util.List;
import java.util.UUID;

public interface InvoiceService {

    Invoice createInvoice(Invoice invoice);

    Invoice getInvoice(UUID id);

    List<Invoice> getPendingInvoices();

    Invoice updateInvoice(Invoice invoice);

    boolean deleteInvoice(UUID id);

    void approvedTransactions(Invoice invoice);

    void noApprovedTransactions(Invoice invoice);
}
