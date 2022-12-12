package co.eficacia.com.rewardsapp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RewardErrorCode {

    CODE_01("Reward not found"),

    CODE_02("No tiene puntos suficientes");

    private final String message;
}