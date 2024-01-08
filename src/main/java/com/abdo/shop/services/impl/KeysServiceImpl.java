package com.abdo.shop.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.abdo.shop.exceptions.NotFoundException;
import com.abdo.shop.model.entity.ItemEntity;
import com.abdo.shop.model.entity.KeyEntity;
import com.abdo.shop.repositories.KeysRepository;
import com.abdo.shop.services.KeysService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class KeysServiceImpl implements KeysService {

    final private KeysRepository keysRepository;

    @Override
    public String addKey(String name) {
        if (!keysRepository.findById(name).isPresent()) {
            KeyEntity key = KeyEntity.builder()
                    .name(name)
                    .items(new HashSet<ItemEntity>())
                    .build();
            keysRepository.save(key);
        }
        return name;
    }

    @Override
    public void addItemToKey(String name, ItemEntity item) {

        KeyEntity key = keysRepository.findById(name).orElseThrow(() -> new NotFoundException());
        Set<ItemEntity> set = key.getItems();
        set.add(item);
        key.setItems(set);
        keysRepository.save(key);

        // TODO optimize saving the whole set
    }

    @Override
    public void removeItemFromKey(String name, ItemEntity item) {
        KeyEntity key = keysRepository.findById(name).orElseThrow(() -> new NotFoundException());
        Set<ItemEntity> set = key.getItems();
        set.remove(item);
        key.setItems(set);
        keysRepository.save(key);
    }

    @Override
    public List<ItemEntity> getItems(String name) {
        KeyEntity key = keysRepository.findById(name).orElseThrow();
        Set<ItemEntity> set = key.getItems();
        return set.stream().toList();
    }

    @Override
    public List<String> suggestNames(String name) {
        List<KeyEntity> keys = keysRepository.findByNameStartsWith(name, PageRequest.of(0, 5));
        List<String> names = keys.stream().map(KeyEntity::getName).collect(Collectors.toList());
        return names;
    }

    @Override
    public List<KeyEntity> addKeys(List<String> names) {
        List<KeyEntity> keys = new ArrayList<KeyEntity>();
        for (String name : names) {
            Optional<KeyEntity> key = keysRepository.findById(name);
            if (key.isPresent())
                keys.add(key.get());
            else {
                KeyEntity savedKey = keysRepository.save(KeyEntity.builder().name(name).build());
                keys.add(savedKey);
            }
        }
        return keys;
    }

}
