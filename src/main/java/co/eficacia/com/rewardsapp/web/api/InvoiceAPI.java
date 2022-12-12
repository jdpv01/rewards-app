package co.eficacia.com.rewardsapp.web.api;

import co.eficacia.com.rewardsapp.web.dto.InvoiceDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/invoices")
public interface InvoiceAPI {

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create-invoice")
    InvoiceDTO createInvoice(@RequestPart("invoiceImage") MultipartFile invoiceImage, @RequestParam UUID storeId, @RequestParam("promotionIdList") List<UUID> promotionIdList);

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/process-invoice")
    InvoiceDTO processInvoice(@RequestParam UUID invoiceId, @RequestParam boolean decision);

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-invoice")
    InvoiceDTO getInvoice(@RequestParam UUID id);

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-all-invoices")
    List<InvoiceDTO> getInvoices();

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/get-user-invoices")
    List<InvoiceDTO> getUserInvoices(@RequestParam UUID userId);

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update-invoice")
    InvoiceDTO updateInvoice(@RequestParam UUID id, @RequestBody InvoiceDTO invoiceDTO);

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-invoice")
    boolean deleteInvoice(@RequestParam UUID id);
}
