package co.eficacia.com.rewardsapp.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ObjectError {

    private Object objectErrorCode;
    private String message;
}
