package co.eficacia.com.rewardsapp.service;

import co.eficacia.com.rewardsapp.model.Survey;

import java.util.List;
import java.util.UUID;

public interface SurveyService {

    Survey createSurvey(Survey survey);

    Survey getSurvey(UUID id);

    List<Survey> getSurveys();

    Survey updateSurvey(Survey survey);

    boolean deleteSurvey(UUID id);
}
