package com.abdo.shop.model.entity;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class AdminEntity {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @NotNull
    @UniqueElements
    @Column(unique = true, nullable = false) // add constrains to the DB
    private String name;

    @NotBlank
    @NotNull
    private String password;
}
