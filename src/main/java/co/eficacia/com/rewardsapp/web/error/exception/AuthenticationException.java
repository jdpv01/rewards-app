package co.eficacia.com.rewardsapp.web.error.exception;

import co.eficacia.com.rewardsapp.web.error.ObjectError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.security.InvalidParameterException;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationException extends InvalidParameterException {

    private HttpStatus httpStatus;
    private ObjectError entityError;
}