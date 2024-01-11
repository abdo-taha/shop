package com.abdo.shop.services;

import java.util.List;

import com.abdo.shop.model.entity.ItemEntity;
import com.abdo.shop.model.entity.KeyEntity;

public interface KeysService {

    String addKey(String name);

    List<KeyEntity> addKeys(List<String> names);

    // void removeItemFromKey(String name, ItemEntity item);

    List<ItemEntity> getItems(String name);

    List<String> suggestNames(String name);
}