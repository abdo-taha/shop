package com.abdo.shop.services.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.abdo.shop.mappers.ItemMapper;
import com.abdo.shop.model.dto.request.CreateItemRequest;
import com.abdo.shop.model.dto.request.EditItemRequest;
import com.abdo.shop.model.dto.response.ItemResponse;
import com.abdo.shop.model.entity.ItemEntity;
import com.abdo.shop.model.entity.KeyEntity;
import com.abdo.shop.repositories.ItemRepository;
import com.abdo.shop.services.ItemService;
import com.abdo.shop.services.KeysService;
import com.abdo.shop.services.PhotoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    final private ItemRepository itemRepository;
    final private KeysService keysService;
    final private ItemMapper itemMapper;
    final private PhotoService photoService;

    @Override
    public ItemResponse createItem(CreateItemRequest createItemRequest) {
        // TODO add name to keys
        List<KeyEntity> keys = keysService.addKeys(createItemRequest.keys());
        ItemEntity itemEntity = ItemEntity.builder()
                .qr(createItemRequest.qr())
                .name(createItemRequest.name())
                .description(createItemRequest.description())
                .price(createItemRequest.price())
                .quantityInStock(createItemRequest.quantity())
                .keys(keys)
                .lastEdit(LocalDateTime.now())
                .build();

        ItemEntity savedItem = itemRepository.save(itemEntity);
        return itemMapper.itemEntityItemResponse(savedItem);
    }

    @Override
    public ItemResponse getItemById(Long itemId) {
        // TODO throw
        ItemEntity item = itemRepository.findById(itemId).orElseThrow();
        return itemMapper.itemEntityItemResponse(item);

    }

    @Override
    public void deleteItemById(Long itemId) {
        itemRepository.deleteById(itemId);
    }

    @Override
    public ItemResponse editItem(EditItemRequest itemRequest) {
        // TODO throw ... remove mapper
        ItemEntity old = itemRepository.findById(itemRequest.id()).orElseThrow();
        ItemEntity item = itemMapper.editItemRequestItemEntity(itemRequest);
        List<KeyEntity> keys = keysService.addKeys(itemRequest.keys());
        item.setKeys(keys);
        item.setLastEdit(LocalDateTime.now());
        item.setPhotos(old.getPhotos());
        itemRepository.save(item);
        return itemMapper.itemEntityItemResponse(item);
    }

    @Override
    public List<ItemResponse> getItemsWithKeys(List<String> keys) {
        // TODO throw
        // if (keys.isEmpty()) throw();
        // TODO cache + pagination
        System.out.println(keys);
        ArrayList<ItemEntity> items = new ArrayList<ItemEntity>(keysService.getItems(keys.get(0)));
        System.out.println(items.size());
        keys.forEach((key) -> {
            ArrayList<ItemEntity> temp = new ArrayList<ItemEntity>(keysService.getItems(key));
            System.out.println(temp.size());
            items.retainAll(temp);
            System.out.println(items.size());

        });
        return items.stream().map(itemMapper::itemEntityItemResponse).collect(Collectors.toList());
    }

    @Override
    public ItemResponse getItemByQr(String qr) {
        // TODO throw
        ItemEntity item = itemRepository.findByQr(qr).orElseThrow();
        return itemMapper.itemEntityItemResponse(item);
    }

    @Override
    public List<ItemResponse> getItemsWithQr(String qr) {
        List<ItemEntity> items = itemRepository.findByQrStartsWith(qr);
        return items.stream().map(itemMapper::itemEntityItemResponse).collect(Collectors.toList());
    }

    @Override
    public ItemEntity getItemEntityById(Long itemId) {
        ItemEntity item = itemRepository.findById(itemId).orElseThrow();
        return item;
    }

    @Override
    public void addQuantity(Long id, Integer quantity) {
        // TODO throw
        ItemEntity item = itemRepository.findById(id).orElseThrow();
        item.setQuantityInStock(item.getQuantityInStock() + quantity);
        itemRepository.save(item);
    }

    @Override
    public String addPhoto(MultipartFile photo, Long id) throws IllegalStateException, IOException {
        return photoService.addPhoto(photo, getItemEntityById(id));
    }

    @Override
    public List<ItemResponse> getAll() {
        return itemRepository.findAll().stream().map(itemMapper::itemEntityItemResponse).collect(Collectors.toList());
    }

    @Override
    public List<String> getPhotos(Long id) {
        // TODO throw
        ItemEntity item = itemRepository.findById(id).orElseThrow();
        return item.getPhotos().stream().map((photo) -> photoService.getURL(photo.getId())).toList();
    }

}
