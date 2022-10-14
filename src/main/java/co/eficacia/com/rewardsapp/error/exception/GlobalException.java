package co.eficacia.com.rewardsapp.error.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class GlobalException extends RuntimeException {

    private HttpStatus httpStatus;
    private EntityError entityError;
}
