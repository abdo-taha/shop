package com.abdo.shop.controllers;

import java.io.IOException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.abdo.shop.model.dto.request.CreateItemRequest;
import com.abdo.shop.model.dto.request.EditItemRequest;
import com.abdo.shop.model.dto.request.SearchRequest;
import com.abdo.shop.model.dto.response.ItemResponse;

import com.abdo.shop.services.ItemService;
import com.abdo.shop.services.PhotoService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/v1/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final PhotoService photoService;

    @GetMapping("")
    public ResponseEntity<List<ItemResponse>> getAllItems() {
        return ResponseEntity.ok(itemService.getAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> getItem(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ItemResponse>> searchWithKeys(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(itemService.getItemsWithKeys(searchRequest.keys()));
    }

    @PostMapping("")
    public ResponseEntity<ItemResponse> addItem(@RequestBody CreateItemRequest createItemRequest) {
        return ResponseEntity.created(null).body(itemService.createItem(createItemRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItemById(id);
        return ResponseEntity.ok(null);
    }

    @PatchMapping("/{id}/add")
    public ResponseEntity<Void> addQuantity(@PathVariable Long id,
            @RequestParam Integer quantity) {
        itemService.addQuantity(id, quantity);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/qr/{qr}")
    public ResponseEntity<ItemResponse> findByQr(@PathVariable String qr) {
        return ResponseEntity.ok(itemService.getItemByQr(qr));
    }

    @GetMapping("/qr")
    public ResponseEntity<List<ItemResponse>> searchWithQr(@RequestParam String qr) {
        return ResponseEntity.ok(itemService.getItemsWithQr(qr));
    }

    @PostMapping("/{id}/photo")
    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file, @PathVariable Long id)
            throws IllegalStateException,
            IOException {

        return ResponseEntity.ok().body(itemService.addPhoto(file, id));
    }

    @PutMapping("/{id}") // TODO
    public ResponseEntity<ItemResponse> editItem(@PathVariable Long id, @RequestBody EditItemRequest editItemRequest) {

        return ResponseEntity.ok(itemService.editItem(editItemRequest));
    }

    @DeleteMapping("/photo/{id}")
    public ResponseEntity<Void> deletePhoto(@PathVariable Long id) {
        photoService.deletePhoto(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}/photo")
    public ResponseEntity<List<String>> getPhotosURLs(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.getPhotos(id));
    }

}
