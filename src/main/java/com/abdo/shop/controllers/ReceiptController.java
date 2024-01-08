package com.abdo.shop.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdo.shop.model.dto.request.NewReceiptRequest;
import com.abdo.shop.services.ReceiptService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/receipt")
public class ReceiptController {

    final private ReceiptService receiptService;

    @PostMapping("")
    public ResponseEntity<Void> postMethodName(@RequestBody NewReceiptRequest newReceiptRequest) {

        receiptService.createReceipt(newReceiptRequest);
        return ResponseEntity.ok(null);
    }

}
