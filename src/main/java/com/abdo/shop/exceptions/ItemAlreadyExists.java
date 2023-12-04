package com.abdo.shop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ItemAlreadyExists extends ResponseStatusException {

    public ItemAlreadyExists(HttpStatus status) {
        super(status);
    }
    
    public ItemAlreadyExists(HttpStatus status, String reason) {
        super(status, reason);
    }

}
