package com.abdo.shop.services;

import com.abdo.shop.model.entity.ItemEntity;
import com.abdo.shop.model.entity.ReceiptEntity;
import com.abdo.shop.model.entity.SoldItemEntity;

public interface SoldItemService {
    SoldItemEntity addItem(Integer quantity, ItemEntity item, ReceiptEntity receipt);

    void deleteItem(Long id);

    void editItem(Long id, Integer quantity);
}
