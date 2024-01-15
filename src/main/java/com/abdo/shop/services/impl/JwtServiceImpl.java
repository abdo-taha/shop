package com.abdo.shop.services.impl;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.abdo.shop.config.SecretsConfigProperties;
import com.abdo.shop.services.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class JwtServiceImpl implements JwtService {

    private final SecretsConfigProperties secretsConfigProperties;
    // private String SECRET_KEY ;

    @Override
    public Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload();
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public boolean isTokenValid(String token) {
        // TODO check database
        return !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretsConfigProperties.hashingSecretKey()));
    }

    @Override
    public String generateToken(String userName, boolean isAdmin) {

        // System.out.println(System.currentTimeMillis() + 1L *1000 * 60 * 60 * 24 *
        // 30);
        // System.out.println(new Date());
        return Jwts
                .builder()
                .subject(userName)
                .claim("isAdmin", isAdmin)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + secretsConfigProperties.JwtExpiration()))
                .signWith(getSecretKey())
                .compact();
    }

}
