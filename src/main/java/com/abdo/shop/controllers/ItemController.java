package com.abdo.shop.controllers;

import java.io.IOException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.abdo.shop.model.dto.ItemDto;
import com.abdo.shop.services.ItemService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    
    @GetMapping
    public List<ItemDto> getAllItems() {
        return itemService.getAllItems();
    }

    @PostMapping
    public ResponseEntity<ItemDto> addItem(@RequestBody ItemDto item) {
        return ResponseEntity.created(null).body(itemService.createItem(item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        itemService.deleteItemById(id);
        return ResponseEntity.ok("deleted");
    }

    @PostMapping("/{id}/addQuantity")
    public ResponseEntity<ItemDto> addQuantity(@PathVariable Long id, @RequestParam int quantity) {
        ItemDto item = itemService.addQuantity(id, quantity);
        return ResponseEntity.ok().body(item);
    }
    
    @GetMapping("/qr/{qr}")
    public ResponseEntity<ItemDto> findByQr(@PathVariable Long qr) {
        ItemDto item = itemService.getItemByQr(qr);
        if (item != null)
            return ResponseEntity.ok().body(item);
        else
            return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<ItemDto>> searchByKeys(@RequestParam String value) {
        List<ItemDto> items = itemService.searchItemsByKeys(value);
        return ResponseEntity.ok().body(items);
    }


    @PostMapping("/{id}/upload")
    public ResponseEntity<ItemDto> uploadPhoto(@RequestParam("file") MultipartFile file, @PathVariable Long id) throws IllegalStateException, IOException {

        return ResponseEntity.ok().body(itemService.addPhoto(id, file));
    }

    @DeleteMapping("/{id}/upload")
    public ResponseEntity<ItemDto> deletePhoto(@RequestParam String photoName, @PathVariable Long id) {
        return ResponseEntity.ok().body(itemService.deletePhoto(id, photoName));
    }
    
    
}
