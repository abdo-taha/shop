package com.abdo.shop.model.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true) 
    private Long qr;
    @Column(nullable = false)
    private String name;
    private String description;
    private double price;
    private int quantityInStock; //TODO default not working the null converts to 0.0 in controller
    private String photos;
}
