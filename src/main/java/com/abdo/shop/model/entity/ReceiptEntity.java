package com.abdo.shop.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class ReceiptEntity {

    @Id
    @GeneratedValue
    private Long id;

    private double total;

    private LocalDateTime lastEdited;

    @ManyToOne
    private CustomerEntity customer;

    @OneToMany
    private List<SoldItemEntity> items;
}
