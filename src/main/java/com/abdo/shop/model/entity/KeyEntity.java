package com.abdo.shop.model.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "Keys_table")
public class KeyEntity {
    @Id
    private String name;

    @ManyToMany(mappedBy = "keys")
    private Set<ItemEntity> items;

    // public void addItem(ItemEntity itemEntity) {
    // items.add(itemEntity);
    // }

    // public void removeItem(ItemEntity itemEntity) {
    // items.remove(itemEntity);
    // }
}
