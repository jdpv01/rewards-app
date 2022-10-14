package co.eficacia.com.rewardsapp.dto;

import co.eficacia.com.rewardsapp.model.RedeemedTransaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RewardDTO {

    private UUID id;

    private String description;

    private String link;

    private String code;

    private String image;

    private int availableQuantity;

    private int requiredPoints;

    private ZonedDateTime validityDate;

    private List<RedeemedTransaction> redeemedTransactionList;
}
