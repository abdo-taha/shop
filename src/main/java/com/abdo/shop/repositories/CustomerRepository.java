package com.abdo.shop.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abdo.shop.model.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    // List<CustomerEntity> findByNameLike(String name);

    List<CustomerEntity> findByNameStartsWith(String name);

    Optional<CustomerEntity> findByName(String name);
}
