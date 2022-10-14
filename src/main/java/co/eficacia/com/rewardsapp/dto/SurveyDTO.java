package co.eficacia.com.rewardsapp.dto;

import co.eficacia.com.rewardsapp.model.Question;
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
public class SurveyDTO {

    private UUID id;

    private String title;

    private String description;

    private int offeredPoints;

    private ZonedDateTime validityDate;

    private List<Question> questionList;
}
