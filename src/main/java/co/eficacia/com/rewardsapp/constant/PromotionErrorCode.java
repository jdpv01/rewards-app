package co.eficacia.com.rewardsapp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PromotionErrorCode {

    CODE_01("Promotion not found");

    private String message;
}