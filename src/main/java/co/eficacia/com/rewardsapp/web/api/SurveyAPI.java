package co.eficacia.com.rewardsapp.web.api;

import co.eficacia.com.rewardsapp.persistance.model.Survey;
import co.eficacia.com.rewardsapp.web.dto.SurveyDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/surveys")
public interface SurveyAPI {

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-survey")
    SurveyDTO createSurvey(@RequestBody SurveyDTO surveyDTO);

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/get-survey")
    SurveyDTO getSurvey(@RequestParam UUID id);

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/get-all-surveys")
    List<SurveyDTO> getSurveys();

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update-survey")
    SurveyDTO updateSurvey(@RequestParam UUID id, @RequestBody SurveyDTO surveyDTO);

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-survey")
    boolean deleteSurvey(@RequestParam UUID id);

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-surveys-order-validation")
    List<Survey> getSurveysOrderValidity();

}
