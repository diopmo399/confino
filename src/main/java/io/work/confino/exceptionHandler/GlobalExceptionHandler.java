package io.work.confino.exceptionHandler;

import africa.atps.monitordata.exceptions.CustomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import utils.ApiResponse;

import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleBadRequest(Exception ex) {
       return  new ApiResponse(400,ex.getMessage(),null);
    }

    @ExceptionHandler(CustomNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse handleNotFoundException(CustomNotFoundException ex) {
       return  new ApiResponse(404,ex.getMessage(),null);
    }

    @ExceptionHandler(InterruptedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse handleInterruptedException(InterruptedException ex) {
       return  new ApiResponse(501,ex.getMessage(),null);
    }

}
