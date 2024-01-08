package com.abdo.shop.model.dto.response;

import java.util.List;

import lombok.Builder;

@Builder
public record PageOfCustomers(Boolean hasNext, List<CustomerResponse> customers) {

}
