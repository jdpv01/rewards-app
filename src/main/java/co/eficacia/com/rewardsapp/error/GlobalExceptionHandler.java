package co.eficacia.com.rewardsapp.error;

import co.eficacia.com.rewardsapp.error.exception.GlobalException;
import co.eficacia.com.rewardsapp.error.exception.LoginException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ObjectError> handleException(GlobalException globalException){
        return new ResponseEntity<>(globalException.getObjectError(),
                globalException.getHttpStatus());
    }

    @ExceptionHandler({LoginException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ObjectError> handleLoginException(LoginException loginException){
        return new ResponseEntity<>(loginException.getEntityError(),
                loginException.getHttpStatus());
    }
}
