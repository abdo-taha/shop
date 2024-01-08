package com.abdo.shop.model.dto.request;

import java.util.List;

public record NewReceiptRequest(String customer, List<SoldItemRequest> items) {

}
