package co.eficacia.com.rewardsapp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RegistrationErrorCode {

    CODE_01("An account with that email address already exists.");

    private final String message;
}

