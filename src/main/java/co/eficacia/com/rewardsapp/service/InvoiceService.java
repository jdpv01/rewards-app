package co.eficacia.com.rewardsapp.service;

import co.eficacia.com.rewardsapp.persistance.model.Invoice;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public interface InvoiceService {

    Invoice createInvoice(MultipartFile multipartFile, UUID storeId, List<UUID> promotionIdList);

    Invoice processInvoice(UUID invoiceId, boolean decision);

    Invoice getInvoice(UUID id);

    List<Invoice> getInvoices();

    List<Invoice> getUserInvoices(UUID userId);

    Invoice updateInvoice(Invoice invoice);

    boolean deleteInvoice(UUID id);
}
