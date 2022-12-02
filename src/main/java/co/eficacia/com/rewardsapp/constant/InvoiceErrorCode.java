package co.eficacia.com.rewardsapp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum InvoiceErrorCode {

    CODE_01("Invoice not found");

    private final String message;

}
