package co.eficacia.com.rewardsapp.dto;

import co.eficacia.com.rewardsapp.model.Invoice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreDTO {

    private String name;

    private String image;

    private List<Invoice> invoiceList;
}
