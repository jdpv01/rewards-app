package co.eficacia.com.rewardsapp.service;

import co.eficacia.com.rewardsapp.persistance.model.Survey;
import co.eficacia.com.rewardsapp.persistance.repository.SurveyRepository;
import co.eficacia.com.rewardsapp.service.impl.SurveyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        List<Survey> listSurveys = new ArrayList<>();
        Survey survey1 = new Survey();
        Survey survey2 = new Survey();
        Survey survey3 = new Survey();
        Survey survey4 = new Survey();
        ZonedDateTime fecha1 = ZonedDateTime.now();
        ZonedDateTime fecha2 = ZonedDateTime.now().minusDays(2);
        ZonedDateTime fecha3 = ZonedDateTime.now().minusDays(3);
        ZonedDateTime fecha4 = ZonedDateTime.now().minusDays(4);
        survey1.setValidityDate(fecha1);
        survey2.setValidityDate(fecha2);
        survey3.setValidityDate(fecha3);
        survey4.setValidityDate(fecha4);
        listSurveys.add(survey2);
        listSurveys.add(survey1);
        listSurveys.add(survey4);
        listSurveys.add(survey3);
        when(surveyRepository.findAll()).thenReturn(listSurveys);
        when(surveyRepository.saveAll(listSurveys)).thenReturn(listSurveys);
        assertEquals(fecha2, listSurveys.get(0).getValidityDate());
        assertEquals(fecha1, listSurveys.get(1).getValidityDate());
        assertEquals(fecha4, listSurveys.get(2).getValidityDate());
        assertEquals(fecha3, listSurveys.get(3).getValidityDate());
        List<Survey> surveys = surveyService.getSurveysOrderValidity();
        assertEquals(fecha1, surveys.get(0).getValidityDate());
        assertEquals(fecha2, surveys.get(1).getValidityDate());
        assertEquals(fecha3, surveys.get(2).getValidityDate());
        assertEquals(fecha4, surveys.get(3).getValidityDate());

    }

}
