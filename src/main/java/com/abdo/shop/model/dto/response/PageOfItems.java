package com.abdo.shop.model.dto.response;

import java.util.List;

import lombok.Builder;

@Builder
public record PageOfItems(
                Boolean hasNext,
                List<ItemResponse> items) {
}
