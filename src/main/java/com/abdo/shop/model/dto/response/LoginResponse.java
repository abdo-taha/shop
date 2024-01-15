package com.abdo.shop.model.dto.response;

import lombok.Builder;

@Builder
public record LoginResponse(String userName, String jwt, boolean isAdmin) {

}
