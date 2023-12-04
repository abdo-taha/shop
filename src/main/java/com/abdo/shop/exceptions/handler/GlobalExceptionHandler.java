package com.abdo.shop.exceptions.handler;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import com.abdo.shop.response.ErrorMessageResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleGlobalException(ResponseStatusException e) {
        return ResponseEntity.status(HttpStatus.valueOf(e.getStatusCode().value())).body(getErrorMessageBody(e));
    } 


    private ErrorMessageResponse getErrorMessageBody(ResponseStatusException e){
        return ErrorMessageResponse.builder()
                .message(e.getReason())
                .createAt(Instant.now())
                .build();
    }
}
