package com.abdo.shop.model.dto.response;

import java.util.List;

public record ReceiptResponse(Long id, Double total, CustomerResponse customer, List<SoldItemResponse> items) {

}
