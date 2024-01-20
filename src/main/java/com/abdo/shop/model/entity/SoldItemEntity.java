package com.abdo.shop.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
// import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
// @Data
@Setter
@Getter
@Builder
public class SoldItemEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Integer quantity;

    @ManyToOne
    private ItemEntity item;

    @ManyToOne
    private ReceiptEntity receipt;

    // public int hashCode() {
    // return id.hashCode();
    // }
}
