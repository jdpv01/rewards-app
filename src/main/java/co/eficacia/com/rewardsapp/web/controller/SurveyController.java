package co.eficacia.com.rewardsapp.web.controller;

import co.eficacia.com.rewardsapp.web.api.SurveyAPI;
import co.eficacia.com.rewardsapp.web.dto.SurveyDTO;
import co.eficacia.com.rewardsapp.mapper.SurveyMapper;
import co.eficacia.com.rewardsapp.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class SurveyController implements SurveyAPI {

    private final SurveyService surveyService;
    private final SurveyMapper surveyMapper;

    @Override
    public SurveyDTO createSurvey(SurveyDTO surveyDTO) {
        return surveyMapper.fromSurvey(surveyService.createSurvey(surveyMapper.fromSurveyDTO(surveyDTO)));
    }

    @Override
    public SurveyDTO getSurvey(UUID id) {
        return surveyMapper.fromSurvey(surveyService.getSurvey(id));
    }

    @Override
    public List<SurveyDTO> getSurveys() {
        return surveyService.getSurveys().stream().map(surveyMapper::fromSurvey).collect(Collectors.toList());
    }

    @Override
    public SurveyDTO updateSurvey(UUID id, SurveyDTO surveyDTO) {
        return surveyMapper.fromSurvey(surveyService.updateSurvey(surveyMapper.fromSurveyDTO(id, surveyDTO)));
    }

    @Override
    public boolean deleteSurvey(UUID id) {
        return surveyService.deleteSurvey(id);
    }
}
