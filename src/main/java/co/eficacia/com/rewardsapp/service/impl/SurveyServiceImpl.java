package co.eficacia.com.rewardsapp.service.impl;

import co.eficacia.com.rewardsapp.constant.SurveyErrorCode;
import co.eficacia.com.rewardsapp.persistance.model.User;
import co.eficacia.com.rewardsapp.service.AccumulatedTransactionService;
import co.eficacia.com.rewardsapp.web.error.ObjectError;
import co.eficacia.com.rewardsapp.web.error.exception.GlobalException;
import co.eficacia.com.rewardsapp.persistance.model.Survey;
import co.eficacia.com.rewardsapp.persistance.repository.SurveyRepository;
import co.eficacia.com.rewardsapp.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;

    @Override
    public Survey createSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }

    @Override
    public Survey getSurvey(UUID id) {
        Optional<Survey> optionalSurvey = surveyRepository.findById(id);
        if(optionalSurvey.isPresent())
            return optionalSurvey.get();
        throw new GlobalException(HttpStatus.NOT_FOUND, new ObjectError(SurveyErrorCode.CODE_01, SurveyErrorCode.CODE_01.getMessage()));
    }

    @Override
    public List<Survey> getSurveys() {
        return getSurveysOrderValidity();
    }

    @Override
    public Survey updateSurvey(Survey survey) {
        Optional<Survey> optionalSurvey = surveyRepository.findById(survey.getId());
        if(optionalSurvey.isPresent())
            return surveyRepository.save(survey);
        throw new GlobalException(HttpStatus.NOT_FOUND, new ObjectError(SurveyErrorCode.CODE_01, SurveyErrorCode.CODE_01.getMessage()));
    }

    @Override
    public boolean deleteSurvey(UUID id) {
        Optional<Survey> optionalSurvey = surveyRepository.findById(id);
        if(optionalSurvey.isPresent()) {
            surveyRepository.delete(optionalSurvey.get());
            return true;
        }
        throw new GlobalException(HttpStatus.NOT_FOUND, new ObjectError(SurveyErrorCode.CODE_01, SurveyErrorCode.CODE_01.getMessage()));
    }

    @Override
    public List<Survey> getSurveysOrderValidity(){
        List<Survey> surveys = StreamSupport.stream(surveyRepository.findAll().spliterator(), false).collect(Collectors.toList());
        surveys.sort((o1, o2) -> o2.getValidityDate().compareTo(o1.getValidityDate()));
        return surveys;
    }

}