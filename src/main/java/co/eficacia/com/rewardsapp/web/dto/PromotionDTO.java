package co.eficacia.com.rewardsapp.web.dto;

import co.eficacia.com.rewardsapp.persistance.model.Invoice;
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
public class PromotionDTO {

    private UUID id;

    private String name;

    private String content;

    private String image;

    private int offeredPoints;

    private int availableQuantity;

    private ZonedDateTime validityDate;
}
