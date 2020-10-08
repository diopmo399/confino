package io.work.confino.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionMessage> resourceNotFound(ResourceNotFoundException ex) {
        ExceptionMessage response = new ExceptionMessage();
        response.setErrorCode(HttpStatus.NOT_FOUND.value());
        response.setErrorMessage(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionMessage> invalidInput(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        ExceptionMessage response = new ExceptionMessage();
        response.setErrorCode(HttpStatus.BAD_REQUEST.value());
        response.setErrorMessage("données envoyées invalides");
        response.setErrors(ValidationUtil.fromBindingErrors(result));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionMessage> illegalArgument(IllegalArgumentException ex) {
        ExceptionMessage response = new ExceptionMessage();
        response.setErrorCode(HttpStatus.BAD_REQUEST.value());
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionMessage> badResquest(BadRequestException ex) {
        ExceptionMessage response = new ExceptionMessage();
        response.setErrorCode(HttpStatus.BAD_REQUEST.value());
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /*@ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ExceptionMessage> badResquestParam(MissingServletRequestParameterException ex) {
        ExceptionMessage response = new ExceptionMessage();
        response.setErrorCode(HttpStatus.BAD_REQUEST.value());
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }*/
}
