package co.eficacia.com.rewardsapp.web.api;

import co.eficacia.com.rewardsapp.web.dto.InvoiceDTO;
import co.eficacia.com.rewardsapp.web.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RequestMapping("/invoices")
public interface InvoiceAPI {
    @PostMapping("/create-invoice")
    InvoiceDTO createInvoice(@RequestBody InvoiceDTO invoiceDTO);

    @GetMapping("/get-invoice/{id}")
    InvoiceDTO getInvoice(@PathVariable UUID id);

    @GetMapping("/get-all-invoices")
    List<InvoiceDTO> getPendingInvoices();

    @PutMapping("/update-invoice/{id}")
    InvoiceDTO updateInvoice(@RequestBody InvoiceDTO invoiceDTO);

    @DeleteMapping("/delete-invoice/{id}")
    boolean deleteInvoice(UUID id);

    // no estoy seguro del getMapping y del userId
    @GetMapping("/get-approved-transaction/{userId}")
    InvoiceDTO approvedTransaction(@RequestBody InvoiceDTO invoiceDTO);

    @GetMapping("/get-no-approved-transaction/{userId}")
    InvoiceDTO noApprovedTransactions(@RequestBody InvoiceDTO invoiceDTO);

    @GetMapping("/get-current-pending-point-user/{userId}")
    Integer currentPendingPointsUser(UserDTO user);
}
