package co.eficacia.com.rewardsapp.error.exception;

import co.eficacia.com.rewardsapp.error.ObjectError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.net.BindException;

@Getter
@Setter
@AllArgsConstructor
public class CustomValidationException extends BindException {

    private HttpStatus httpStatus;
    private ObjectError entityError;
}