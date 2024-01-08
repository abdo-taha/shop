package com.abdo.shop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundException extends ResponseStatusException {

    public NotFoundException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

    public NotFoundException() {
        super(HttpStatus.NOT_FOUND, "NOT found");
    }

}