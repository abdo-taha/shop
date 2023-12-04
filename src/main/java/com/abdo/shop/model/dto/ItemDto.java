package com.abdo.shop.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDto {
    private Long id;
    private Long qr;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String photos;
}

