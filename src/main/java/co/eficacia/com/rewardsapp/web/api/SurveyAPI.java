package co.eficacia.com.rewardsapp.web.api;

import co.eficacia.com.rewardsapp.web.dto.SurveyDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/surveys")
public interface SurveyAPI {

    @PostMapping("/create-survey")
    SurveyDTO createSurvey(@RequestBody SurveyDTO surveyDTO);

    @GetMapping("/get-survey/{id}")
    SurveyDTO getSurvey(@PathVariable UUID id);

    @GetMapping("/get-all-surveys")
    List<SurveyDTO> getSurveys();

    @PutMapping("/update-survey/{id}")
    SurveyDTO updateSurvey(@PathVariable UUID id, @RequestBody SurveyDTO surveyDTO);

    @DeleteMapping("/delete-survey/{id}")
    boolean deleteSurvey(@PathVariable UUID id);
}
