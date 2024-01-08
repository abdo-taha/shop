package com.abdo.shop.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abdo.shop.model.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    // List<CustomerEntity> findByNameLike(String name);

    Page<CustomerEntity> findByNameStartsWith(String name, Pageable pageable);

    Optional<CustomerEntity> findByName(String name);
}
