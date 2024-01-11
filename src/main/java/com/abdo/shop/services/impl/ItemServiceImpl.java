package com.abdo.shop.services.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.abdo.shop.exceptions.NotFoundException;
import com.abdo.shop.mappers.ItemMapper;
import com.abdo.shop.model.dto.request.CreateItemRequest;
import com.abdo.shop.model.dto.request.EditItemRequest;
import com.abdo.shop.model.dto.response.ItemResponse;
import com.abdo.shop.model.dto.response.PageOfItems;
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

        List<KeyEntity> keys = keysService.addKeys(createItemRequest.keys());
        List<KeyEntity> nameKeys = keysService.addKeys(Arrays.asList(createItemRequest.name().split(" ")));
        List<KeyEntity> allKeys = Stream.concat(keys.stream(), nameKeys.stream()).toList();
        ItemEntity itemEntity = ItemEntity.builder()
                .qr(createItemRequest.qr())
                .name(createItemRequest.name())
                .description(createItemRequest.description())
                .price(createItemRequest.price())
                .quantityInStock(createItemRequest.quantity())
                .keys(allKeys)
                .lastEdit(LocalDateTime.now())
                .build();
        // for (KeyEntity key : allKeys)
        // itemEntity.addKey(key);
        ItemEntity savedItem = itemRepository.save(itemEntity);
        return itemMapper.itemEntityItemResponse(savedItem);
    }

    @Override
    public ItemResponse getItemById(Long itemId) {
        ItemEntity item = itemRepository.findById(itemId).orElseThrow(() -> new NotFoundException());
        // System.out.println(item.getKeys());
        return itemMapper.itemEntityItemResponse(item);

    }

    @Override
    public void deleteItemById(Long itemId) {
        itemRepository.deleteById(itemId);
    }

    @Override
    public ItemResponse editItem(EditItemRequest itemRequest) {
        ItemEntity old = itemRepository.findById(itemRequest.id()).orElseThrow(() -> new NotFoundException());
        List<KeyEntity> keys = keysService.addKeys(itemRequest.keys());
        List<KeyEntity> nameKeys = keysService.addKeys(Arrays.asList(itemRequest.name().split(" ")));
        List<KeyEntity> allKeys = Stream.concat(keys.stream(), nameKeys.stream()).toList();
        // No mapper for forward compatibility

        old.setQr(itemRequest.qr());
        old.setName(itemRequest.name());
        old.setDescription(itemRequest.description());
        old.setPrice(itemRequest.price());
        old.setQuantityInStock(itemRequest.quantity());
        old.setLastEdit(LocalDateTime.now());
        old.setKeys(allKeys);
        // keys that in old and not in new
        // remove relation
        System.out.println("aaaaaaaaaa");
        itemRepository.save(old);
        System.out.println("bbbbbbbbbbbbb");
        return itemMapper.itemEntityItemResponse(old);
    }

    @Override
    public List<ItemResponse> getItemsWithKeys(List<String> keys) {
        if (keys.isEmpty())
            throw (new NotFoundException());
        // TODO cache + pagination
        ArrayList<ItemEntity> items = new ArrayList<ItemEntity>(keysService.getItems(keys.get(0)));
        keys.forEach((key) -> {
            ArrayList<ItemEntity> temp = new ArrayList<ItemEntity>(keysService.getItems(key));
            items.retainAll(temp);

        });
        return items.stream().map(itemMapper::itemEntityItemResponse).collect(Collectors.toList());
    }

    @Override
    public ItemResponse getItemByQr(String qr) {

        ItemEntity item = itemRepository.findByQr(qr).orElseThrow(() -> new NotFoundException());
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

        ItemEntity item = itemRepository.findById(id).orElseThrow(() -> new NotFoundException());
        item.setQuantityInStock(item.getQuantityInStock() + quantity);
        itemRepository.save(item);
    }

    @Override
    public String addPhoto(MultipartFile photo, Long id) throws IllegalStateException, IOException {
        return photoService.addPhoto(photo, getItemEntityById(id));
    }

    @Override
    public PageOfItems getAll(Integer page) {
        Page<ItemEntity> items = itemRepository.findAll(PageRequest.of(page, 5));

        PageOfItems itemResponsePage = PageOfItems.builder().hasNext(items.hasNext())
                .items(items.getContent().stream().map(itemMapper::itemEntityItemResponse).toList()).build();
        return itemResponsePage;
    }

    @Override
    public List<String> getPhotos(Long id) {
        ItemEntity item = itemRepository.findById(id).orElseThrow(() -> new NotFoundException());
        return item.getPhotos().stream().map((photo) -> photoService.getURL(photo.getId())).toList();
    }

}
