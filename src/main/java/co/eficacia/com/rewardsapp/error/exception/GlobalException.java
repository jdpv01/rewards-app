package co.eficacia.com.rewardsapp.error.exception;

import co.eficacia.com.rewardsapp.error.ObjectError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class GlobalException extends RuntimeException {

    private HttpStatus httpStatus;
    private ObjectError objectError;
}
