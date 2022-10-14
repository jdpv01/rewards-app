package co.eficacia.com.rewardsapp.dto;

import co.eficacia.com.rewardsapp.model.Comment;
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
public class PublicationDTO {

    private UUID id;

    private String image;

    private String title;

    private String description;

    private int availableQuantity;

    private int offeredPoints;

    private ZonedDateTime validityDate;

    private List<Comment> commentList;
}
