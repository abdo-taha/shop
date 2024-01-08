package com.abdo.shop.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class SoldItemEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Long quantity;

    @ManyToOne
    private ItemEntity item;

    @ManyToOne
    private ReceiptEntity receipt;

}
