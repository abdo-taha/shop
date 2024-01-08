package com.abdo.shop.services.impl;

import org.springframework.stereotype.Service;

import com.abdo.shop.exceptions.NotFoundException;
import com.abdo.shop.model.entity.ItemEntity;
import com.abdo.shop.model.entity.ReceiptEntity;
import com.abdo.shop.model.entity.SoldItemEntity;
import com.abdo.shop.repositories.SoldItemRepository;
import com.abdo.shop.services.SoldItemService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ItemSoldServiceImpl implements SoldItemService {
    final private SoldItemRepository soldItemRepository;

    @Override
    public Long addItem(Integer quantity, ItemEntity item, ReceiptEntity receipt) {
        SoldItemEntity soldItemEntity = SoldItemEntity.builder()
                .quantity(quantity)
                .item(item)
                .receipt(receipt)
                .build();
        SoldItemEntity savedItem = soldItemRepository.save(soldItemEntity);
        return savedItem.getId();
    }

    @Override
    public void deleteItem(Long id) {
        soldItemRepository.deleteById(id);
    }

    @Override
    public void editItem(Long id, Integer quantity) {
        SoldItemEntity item = soldItemRepository.findById(id).orElseThrow(() -> new NotFoundException());
        item.setQuantity(quantity);
    }

}
