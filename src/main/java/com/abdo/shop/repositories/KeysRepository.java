package com.abdo.shop.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abdo.shop.model.entity.KeyEntity;

@Repository
public interface KeysRepository extends JpaRepository<KeyEntity, String> {

    // List<KeyEntity> findByNameLike(String name, Pageable pageable);

    List<KeyEntity> findByNameStartsWith(String name, Pageable pageable);
}
