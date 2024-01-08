package com.abdo.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abdo.shop.model.entity.SoldItemEntity;

@Repository
public interface SoldItemRepository extends JpaRepository<SoldItemEntity, Long> {

}
