package co.eficacia.com.rewardsapp.web.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ObjectError {

    private Object objectErrorCode;
    private String message;
}
