package com.abdo.shop.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "item_table")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true, nullable = true)
    private String qr;
    @NotBlank
    @NotNull
    private String name;
    private String description;
    private double price;
    private Integer quantityInStock;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<PhotoEntity> photos;
    // @Builder.Default
    @ManyToMany
    @JoinTable(name = "item_keys", joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns = @JoinColumn(name = "key_id"))
    private List<KeyEntity> keys;// = new HashSet<KeyEntity>();
    private LocalDateTime lastEdit;

    // public void addKey(KeyEntity key) {
    // this.keys.add(key);
    // key.addItem(this);
    // }

    // public void removeKey(KeyEntity key) {
    // this.keys.remove(key);
    // key.removeItem(this);
    // }
}
