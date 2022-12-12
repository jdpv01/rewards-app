package co.eficacia.com.rewardsapp.web.error;

import co.eficacia.com.rewardsapp.web.error.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.security.InvalidParameterException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus
    public ResponseEntity<ObjectError> handleException(CustomException customException){
        return new ResponseEntity<>(customException.getObjectError(), customException.getHttpStatus());
    }
}
