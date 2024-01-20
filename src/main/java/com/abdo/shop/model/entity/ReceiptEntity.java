package com.abdo.shop.model.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
// import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

    private Double total;

    private LocalDateTime lastEdited;

    @ManyToOne
    private CustomerEntity customer;

    @Builder.Default
    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SoldItemEntity> items = new HashSet<SoldItemEntity>();

    public void addItem(SoldItemEntity item) {
        items.add(item);
        // item.setReceipt(this);
    }

    public void removeItem(SoldItemEntity item) {
        items.remove(item);
    }
}
