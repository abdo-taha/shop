package com.abdo.shop.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("secrets")
public record SecretsConfigProperties(String hashingSecretKey, String localImagePath, String globalImageRelativePath,
                String publicUrl, Long JwtExpiration) {
}
