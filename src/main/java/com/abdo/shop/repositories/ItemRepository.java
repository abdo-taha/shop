package com.abdo.shop.repositories;





import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abdo.shop.model.entity.ItemEntity;

@Repository
public interface ItemRepository extends CrudRepository<ItemEntity,Long> {

    public ItemEntity findByQr(Long qr);

    public List<ItemEntity> findByDescriptionContaining(String key);
} 
