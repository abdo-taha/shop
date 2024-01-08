package com.abdo.shop.services;

import com.abdo.shop.model.entity.ItemEntity;
import com.abdo.shop.model.entity.ReceiptEntity;

public interface SoldItemService {
    Long addItem(Long quantity, ItemEntity item, ReceiptEntity receipt);

    void deleteItem(Long id);

    void editItem(Long id, Long quantity);
}
