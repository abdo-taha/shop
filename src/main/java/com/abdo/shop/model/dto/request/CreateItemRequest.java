package com.abdo.shop.model.dto.request;

import java.util.List;

public record CreateItemRequest(String qr, String name, String description, double price, Integer quantity,
        List<String> keys) {

}
