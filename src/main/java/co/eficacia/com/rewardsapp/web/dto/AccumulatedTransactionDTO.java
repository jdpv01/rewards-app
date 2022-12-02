package co.eficacia.com.rewardsapp.web.dto;

import co.eficacia.com.rewardsapp.persistance.model.Invoice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccumulatedTransactionDTO {

    private String source;

    private int productQuantity;

    private int accumulatedPoints;

    private ZonedDateTime timestamp;

    private Invoice invoice;
}
