package co.eficacia.com.rewardsapp.web.dto;

import co.eficacia.com.rewardsapp.persistance.model.AccumulatedTransaction;
import co.eficacia.com.rewardsapp.persistance.model.Promotion;
import co.eficacia.com.rewardsapp.persistance.model.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {

    private Store store;

    private String image;

    private String code;

    private String state;

    private ZonedDateTime timestamp;

    private AccumulatedTransaction accumulatedTransaction;

}
