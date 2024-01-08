package com.abdo.shop.model.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;

@Builder
public record ItemResponse(Long id, String qr, String name, String description, double price, Integer quantity,
        List<String> keys, LocalDateTime lastEdit) {

}
