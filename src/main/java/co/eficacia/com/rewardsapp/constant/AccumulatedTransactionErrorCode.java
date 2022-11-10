package co.eficacia.com.rewardsapp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AccumulatedTransactionErrorCode {

    CODE_01("AccumulatedTransaction no found");

    private final String message;
}



