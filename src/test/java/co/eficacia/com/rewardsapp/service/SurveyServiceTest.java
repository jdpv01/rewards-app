package co.eficacia.com.rewardsapp.service;

import co.eficacia.com.rewardsapp.persistance.repository.SurveyRepository;
import co.eficacia.com.rewardsapp.service.impl.SurveyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class SurveyServiceTest {

    private SurveyService surveyService;

    private SurveyRepository surveyRepository;

    @BeforeEach
    public void init() {
        surveyRepository = mock(SurveyRepository.class);
        surveyService = new SurveyServiceImpl(surveyRepository);
    }

    @Test
    public void testGetSurveysOrderValidity (){

    }

}
