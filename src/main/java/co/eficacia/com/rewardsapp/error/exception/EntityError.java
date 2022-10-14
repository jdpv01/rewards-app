package co.eficacia.com.rewardsapp.error.exception;

import co.eficacia.com.rewardsapp.constant.UserErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntityError<T> {

    private T entityErrorCode;
    private String message;
}
