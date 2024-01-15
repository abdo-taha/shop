package com.abdo.shop.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.lang.Function;

public interface JwtService {

    public Claims extractAllClaims(String token);

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    public String generateToken(String userName, boolean isAdmin);

    // public String generateToken(Map<String, Object> extraClaims, UserLoginRequest
    // user);

    public String extractUsername(String token);

    public boolean isTokenValid(String token);

}