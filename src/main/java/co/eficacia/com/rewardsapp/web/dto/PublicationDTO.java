package co.eficacia.com.rewardsapp.web.dto;

import co.eficacia.com.rewardsapp.persistance.model.Comment;
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
public class PublicationDTO {

    private String image;

    private String title;

    private String link;

    private String description;

    private int availableQuantity;

    private int offeredPoints;

    private ZonedDateTime validityDate;

}
