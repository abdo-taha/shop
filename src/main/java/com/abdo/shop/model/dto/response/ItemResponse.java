package com.abdo.shop.model.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.Builder;

@Builder
public record ItemResponse(Long id, String qr, String name, String description, double price, Integer quantity,
                Set<String> keys, LocalDateTime lastEdit) {

}
