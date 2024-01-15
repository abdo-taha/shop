package com.abdo.shop.model.dto.response;

import com.abdo.shop.model.entity.Role;

import lombok.Builder;

@Builder
public record WorkerResponse(Long id, String name, Role roles) {

}
