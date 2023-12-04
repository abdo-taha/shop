package com.abdo.shop.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.abdo.shop.model.dto.ItemDto;

public interface ItemService {

    public ItemDto createItem(ItemDto item);

    public Optional<ItemDto> getItemById(Long itemId);
    
    public void deleteItemById(Long itemId);
    
    public ItemDto editItem(ItemDto item);

    public List<ItemDto> getAllItems();

    public ItemDto addPhoto(Long id, MultipartFile file) throws IllegalStateException, IOException;
    
    public ItemDto deletePhoto(Long id, String photo);
    
    public ItemDto addQuantity(Long id, int quantity);

    public Boolean isPresent(Long id);

    public ItemDto getItemByQr(Long qr);

    public List<ItemDto> searchItemsByKeys(String keys);
}
