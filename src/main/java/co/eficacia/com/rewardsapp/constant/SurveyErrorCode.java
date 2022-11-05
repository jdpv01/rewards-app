package co.eficacia.com.rewardsapp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SurveyErrorCode {

    CODE_01("Survey not found");

    private final String message;
}