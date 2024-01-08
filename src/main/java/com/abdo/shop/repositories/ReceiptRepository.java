package com.abdo.shop.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abdo.shop.model.entity.CustomerEntity;
import com.abdo.shop.model.entity.ReceiptEntity;

@Repository
public interface ReceiptRepository extends JpaRepository<ReceiptEntity, Long> {

    Page<ReceiptEntity> findByCustomer(CustomerEntity customer, Pageable pageable);
}
