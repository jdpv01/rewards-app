package co.eficacia.com.rewardsapp.mapper;

import co.eficacia.com.rewardsapp.persistance.model.Invoice;
import co.eficacia.com.rewardsapp.web.dto.InvoiceDTO;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    
    Invoice fromInvoiceDTO(InvoiceDTO invoiceDTO);

    Invoice fromInvoiceDTO(UUID id, InvoiceDTO invoiceDTO);

    InvoiceDTO fromInvoice(Invoice invoice);
}
