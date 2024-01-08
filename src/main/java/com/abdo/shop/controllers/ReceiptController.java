package com.abdo.shop.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abdo.shop.model.dto.request.NewReceiptRequest;
import com.abdo.shop.model.dto.response.PageOfReceipts;
import com.abdo.shop.model.dto.response.ReceiptResponse;
import com.abdo.shop.services.CustomerService;
import com.abdo.shop.services.ReceiptService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/receipt")
public class ReceiptController {

    final private ReceiptService receiptService;
    final private CustomerService customerService;

    @PostMapping("")
    public ResponseEntity<ReceiptResponse> postMethodName(@RequestBody NewReceiptRequest newReceiptRequest) {

        return ResponseEntity.ok(customerService.createReceipt(newReceiptRequest));
    }

    @GetMapping("")
    public ResponseEntity<PageOfReceipts> getALlReceipts(@RequestParam(defaultValue = "0") Integer page) {
        return ResponseEntity.ok(receiptService.getReceipts(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceiptResponse> getReceipt(@PathVariable Long id) {

        return ResponseEntity.ok(receiptService.getReceipt(id));
    }

    @DeleteMapping("/{id}")
    public void deleteReceipt(@PathVariable Long id) {
        receiptService.deleteReceipt(id);
    }

}
