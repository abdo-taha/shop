package com.abdo.shop.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdo.shop.model.dto.request.NameRequest;
import com.abdo.shop.services.KeysService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/keys")
public class KeysController {

    final private KeysService keysService;

    @GetMapping("/suggest")
    public ResponseEntity<List<String>> getSuggestions(@RequestParam String name) {
        return ResponseEntity.ok(keysService.suggestNames(name));
    }

    @PostMapping("")
    public ResponseEntity<String> createKey(@RequestBody NameRequest nameRequest) {

        return ResponseEntity.ok(keysService.addKey(nameRequest.name()));
    }

}
