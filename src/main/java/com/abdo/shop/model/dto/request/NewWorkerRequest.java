package com.abdo.shop.model.dto.request;

import lombok.Builder;

@Builder
public record NewWorkerRequest(String name, String password, Boolean admin) {

}
