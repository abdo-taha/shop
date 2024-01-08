package com.abdo.shop.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.abdo.shop.model.dto.request.CreateItemRequest;
import com.abdo.shop.model.dto.request.EditItemRequest;
import com.abdo.shop.model.dto.response.ItemResponse;
import com.abdo.shop.model.dto.response.PageOfItems;
import com.abdo.shop.model.entity.ItemEntity;

public interface ItemService {

    public ItemResponse createItem(CreateItemRequest createItemRequest);

    public ItemResponse getItemById(Long itemId);

    public ItemEntity getItemEntityById(Long itemId);

    public void deleteItemById(Long itemId);

    public ItemResponse editItem(EditItemRequest item);

    public void addQuantity(Long id, Integer quantity);

    public List<ItemResponse> getItemsWithKeys(List<String> keys);

    public List<ItemResponse> getItemsWithQr(String qr);

    public ItemResponse getItemByQr(String qr);

    String addPhoto(MultipartFile photo, Long id) throws IllegalStateException, IOException;

    public PageOfItems getAll(Integer page);

    public List<String> getPhotos(Long id);
}
