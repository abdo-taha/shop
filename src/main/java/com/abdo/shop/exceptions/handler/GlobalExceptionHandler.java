package com.abdo.shop.exceptions.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import com.abdo.shop.model.dto.response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleGlobalException(ResponseStatusException e) {
        return ResponseEntity.status(HttpStatus.valueOf(e.getStatusCode().value())).body(getErrorMessageBody(e));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleLocalException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getErrorMessageBody(e));
    }

    private ErrorResponse getErrorMessageBody(ResponseStatusException e) {
        return ErrorResponse.builder()
                .message(e.getReason())
                .build();
    }

    //
    @Value("${spring.profiles.active}")
    private String activeProfile;

    private ErrorResponse getErrorMessageBody(Exception e) {
        return ErrorResponse.builder()
                .message("dev".equals(activeProfile) ? e.getMessage() : "internal error")
                .build();
    }
}
