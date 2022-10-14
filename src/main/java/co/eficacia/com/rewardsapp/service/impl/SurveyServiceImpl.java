package co.eficacia.com.rewardsapp.service.impl;

import co.eficacia.com.rewardsapp.constant.SurveyErrorCode;
import co.eficacia.com.rewardsapp.error.exception.EntityError;
import co.eficacia.com.rewardsapp.error.exception.GlobalException;
import co.eficacia.com.rewardsapp.mapper.SurveyMapper;
import co.eficacia.com.rewardsapp.model.Survey;
import co.eficacia.com.rewardsapp.repository.SurveyRepository;
import co.eficacia.com.rewardsapp.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;
    private final SurveyMapper surveyMapper;

    @Override
    public Survey createSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }

    @Override
    public Survey getSurvey(UUID id) {
        Optional<Survey> optionalSurvey = surveyRepository.findById(id);
        if(optionalSurvey.isPresent())
            return optionalSurvey.get();
        throw new GlobalException(HttpStatus.NOT_FOUND, new EntityError(SurveyErrorCode.CODE_01, SurveyErrorCode.CODE_01.getMessage()));
    }

    @Override
    public List<Survey> getSurveys() {
        return StreamSupport.stream(surveyRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Survey updateSurvey(Survey survey) {
        Optional<Survey> optionalSurvey = surveyRepository.findById(survey.getId());
        if(optionalSurvey.isPresent())
            return surveyRepository.save(survey);
        throw new GlobalException(HttpStatus.NOT_FOUND, new EntityError(SurveyErrorCode.CODE_01, SurveyErrorCode.CODE_01.getMessage()));
    }

    @Override
    public boolean deleteSurvey(UUID id) {
        Optional<Survey> optionalSurvey = surveyRepository.findById(id);
        if(optionalSurvey.isPresent()) {
            surveyRepository.delete(optionalSurvey.get());
            return true;
        }
        throw new GlobalException(HttpStatus.NOT_FOUND, new EntityError(SurveyErrorCode.CODE_01, SurveyErrorCode.CODE_01.getMessage()));
    }
}