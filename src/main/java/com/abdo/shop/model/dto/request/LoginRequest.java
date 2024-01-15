package com.abdo.shop.model.dto.request;

import lombok.Builder;

@Builder
public record LoginRequest(String name, String Password) {

}
