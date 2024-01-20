package com.abdo.shop.model.dto.response;

import java.util.Set;

public record ReceiptResponse(Long id, Double total, CustomerResponse customer, Set<SoldItemResponse> items) {

}
