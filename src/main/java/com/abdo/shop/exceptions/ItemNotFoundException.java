package com.abdo.shop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ItemNotFoundException extends ResponseStatusException {

    public ItemNotFoundException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public ItemNotFoundException(HttpStatus status) {
        super(status);
    }
}