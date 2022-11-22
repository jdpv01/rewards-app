package co.eficacia.com.rewardsapp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RegistrationErrorCode {

    CODE_01("Invalid password.");

    private final String message;
}

