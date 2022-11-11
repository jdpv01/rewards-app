package co.eficacia.com.rewardsapp.web.error;

import co.eficacia.com.rewardsapp.web.error.exception.GlobalException;
import co.eficacia.com.rewardsapp.web.error.exception.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler
//    public ResponseEntity<CustomObjectError> handleException(GlobalException globalException){
//        return new ResponseEntity<>(globalException.getCustomObjectError(),
//                globalException.getHttpStatus());
//    }
//
//    @ExceptionHandler({AuthenticationException.class})
//    @ResponseBody
//    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
//    public ResponseEntity<CustomObjectError> handleLoginException(AuthenticationException authenticationException){
//        return new ResponseEntity<>(authenticationException.getEntityError(),
//                authenticationException.getHttpStatus());
//    }
}
