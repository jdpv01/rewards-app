package co.eficacia.com.rewardsapp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PublicationDTO {

    private String image;

    private String title;

    private String link;

    private String description;

    private int availableQuantity;

    private int offeredPoints;

    private ZonedDateTime validityDate;
}
