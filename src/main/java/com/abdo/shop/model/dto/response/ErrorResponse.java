package com.abdo.shop.model.dto.response;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String message) {
}
