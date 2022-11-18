package co.eficacia.com.rewardsapp.web.error;

import co.eficacia.com.rewardsapp.web.error.exception.GlobalException;
import co.eficacia.com.rewardsapp.web.error.exception.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ObjectError> handleException(GlobalException globalException){
        return new ResponseEntity<>(globalException.getObjectError(), globalException.getHttpStatus());
    }

    @ExceptionHandler({AuthenticationException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ObjectError> handleAuthenticationException(AuthenticationException authenticationException){
        return new ResponseEntity<>(authenticationException.getObjectError(), authenticationException.getHttpStatus());
    }
}
