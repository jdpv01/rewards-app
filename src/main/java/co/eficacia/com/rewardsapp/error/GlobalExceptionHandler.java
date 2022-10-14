package co.eficacia.com.rewardsapp.error;

import co.eficacia.com.rewardsapp.error.exception.GlobalException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleException(GlobalException globalException){
        return new ResponseEntity<>(globalException.getEntityError(),
                globalException.getHttpStatus());
    }
}
