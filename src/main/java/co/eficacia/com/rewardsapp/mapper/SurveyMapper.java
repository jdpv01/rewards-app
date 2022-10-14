package co.eficacia.com.rewardsapp.mapper;

import co.eficacia.com.rewardsapp.dto.SurveyDTO;
import co.eficacia.com.rewardsapp.model.Survey;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface SurveyMapper {

    Survey fromSurveyDTO(SurveyDTO surveyDTO);

    Survey fromSurveyDTO(UUID id, SurveyDTO surveyDTO);

    SurveyDTO fromSurvey(Survey survey);
}