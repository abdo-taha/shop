package com.abdo.shop.model.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    WORKER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}