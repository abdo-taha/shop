package com.abdo.shop.services.impl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.abdo.shop.exceptions.ItemAlreadyExists;
import com.abdo.shop.exceptions.ItemNotFoundException;
import com.abdo.shop.mappers.ItemMapper;
import com.abdo.shop.model.dto.ItemDto;
import com.abdo.shop.model.entity.ItemEntity;
import com.abdo.shop.repositories.ItemRepository;
import com.abdo.shop.services.ItemService;
import com.abdo.shop.utils.PhotosUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final PhotosUtils photosUtils;


    String imagesPath = System.getProperty("user.dir") + "\\data\\images\\"; 

    @Override
    public Optional<ItemDto> getItemById(Long itemId) {
        return itemRepository.findById(itemId).map(itemMapper::itemEntityTOItemDto);
    }

    @Override
    public void deleteItemById(Long itemId) {
        if (!isPresent(itemId))
            throw new ItemNotFoundException(HttpStatus.BAD_REQUEST);
        itemRepository.deleteById(itemId);
    }

    @Override
    public ItemDto editItem(ItemDto item) {
        if (!isPresent(item.getId())) {
            throw new ItemNotFoundException(HttpStatus.NOT_FOUND, "item not found with id" + item.getId());
        }
        ItemEntity itemEntity = itemMapper.itemDtoToItemEntity(item);
        ItemEntity savedItem = itemRepository.save(itemEntity);
        return itemMapper.itemEntityTOItemDto(savedItem);
    }

    @Override
    public ItemDto createItem(ItemDto item) {
        if (getItemByQr(item.getQr()) != null)
            throw new ItemAlreadyExists(HttpStatus.BAD_REQUEST,"there is another item with this qr");
        ItemEntity itemEntity = itemMapper.itemDtoToItemEntity(item);
        ItemEntity savedItem = itemRepository.save(itemEntity);
        return itemMapper.itemEntityTOItemDto(savedItem);
    }

    @Override
    public List<ItemDto> getAllItems() {
        List<ItemEntity> items = (List<ItemEntity>) itemRepository.findAll();
        return items.stream()
                .map(itemMapper::itemEntityTOItemDto)
                .collect(Collectors.toList());
    }

    @Override
    public ItemDto addPhoto(Long id, MultipartFile file) throws IllegalStateException, IOException {
        ItemDto item = getItemById(id).get();
        if (item == null)
            return null;
        String path = imagesPath + id;
        File directory = new File(path);
        if (!directory.exists())
            directory.mkdirs();
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss.SSS");
        String fileName = time.format(formatter)+"."+ photosUtils.getExtensionByStringHandling(file.getOriginalFilename());
        path += "\\" + fileName;
        file.transferTo(new File(path));

        String itemPhotos = (item.getPhotos() == null?"":item.getPhotos()) + " " + fileName;
        item.setPhotos(itemPhotos);
        editItem(item);
        return item;
    }

    @Override
    public ItemDto deletePhoto(Long id, String photo) {
        String path = imagesPath + id + "\\" + photo;
        File file = new File(path);
        file.delete();
        ItemDto item = getItemById(id).get();
        String itemPhotos = item.getPhotos().replace(" " + photo, "");
        item.setPhotos(itemPhotos);
        editItem(item);
        return item;
    }

    @Override
    public ItemDto addQuantity(Long id, int quantity) {
        if (!isPresent(id)) {
            throw new ItemNotFoundException(HttpStatus.NOT_FOUND);
        }
        ItemDto item = getItemById(id).get();
        item.setQuantity(item.getQuantity() + quantity);
        return editItem(item);
    }

    @Override
    public Boolean isPresent(Long id) {
        return getItemById(id).isPresent();
    }

    @Override
    public ItemDto getItemByQr(Long qr) {
        ItemEntity item = itemRepository.findByQr(qr);
        return itemMapper.itemEntityTOItemDto(item);
    }

    @Override
    public List<ItemDto> searchItemsByKeys(String keys) {
        String[] keysArray = keys.split("\\s+");
        List<String> keysList = Arrays.asList(keysArray);
        if (keysList.isEmpty())
            return null;
        List<ItemEntity> items = itemRepository.findByDescriptionContaining(keysList.get(0));
        keysList.forEach((key) -> {
            items.retainAll(itemRepository.findByDescriptionContaining(key));});

        return items
                .stream()
                .map(itemMapper::itemEntityTOItemDto)
                .collect(Collectors.toList());
    }

    
}
