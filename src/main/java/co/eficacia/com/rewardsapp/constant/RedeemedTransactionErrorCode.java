package co.eficacia.com.rewardsapp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RedeemedTransactionErrorCode {

    CODE_01("RedeemedTransaction not found");

    private final String message;

}
