package co.eficacia.com.rewardsapp.web.dto;

import co.eficacia.com.rewardsapp.persistance.model.RedeemedTransaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RewardDTO {

    private String description;

    private String link;

    private String code;

    private String image;

    private int availableQuantity;

    private int requiredPoints;

    private ZonedDateTime validityDate;

    private List<RedeemedTransaction> redeemedTransactionList;
}
