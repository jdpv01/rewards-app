package co.eficacia.com.rewardsapp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AvailableRewardDTO {

    private UUID id;

    private String description;

    private String link;

    private String code;

    private String image;

    private int availableQuantity;

    private int requiredPoints;

    private ZonedDateTime validityDate;
}
