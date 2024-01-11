package com.abdo.shop.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdo.shop.model.dto.request.EditCustomerRequest;
import com.abdo.shop.model.dto.response.CustomerResponse;
import com.abdo.shop.model.dto.response.PageOfCustomers;
import com.abdo.shop.model.dto.response.PageOfReceipts;
import com.abdo.shop.services.CustomerService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customers")
public class CustomerController {

    final private CustomerService customerService;

    @GetMapping("")
    public ResponseEntity<PageOfCustomers> getAll(@RequestParam(defaultValue = "0") Integer page) {
        return ResponseEntity.ok(customerService.getAll(page));
    }

    @PostMapping("")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody EditCustomerRequest editCustomerRequest) {
        System.out.println(editCustomerRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.createCustomer(editCustomerRequest.name()));
    }

    @PutMapping("")
    public ResponseEntity<CustomerResponse> editCustomer(@RequestBody EditCustomerRequest editCustomerRequest) {

        return ResponseEntity.ok(customerService.EditCustomerName(editCustomerRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @GetMapping("/{id}/receipts")
    public ResponseEntity<PageOfReceipts> getReceipts(@PathVariable @NonNull Long id,
            @RequestParam(defaultValue = "0") Integer page) {
        return ResponseEntity.ok(customerService.getReceiptsByCustomer(id, page));
    }

    @GetMapping("/suggest")
    public ResponseEntity<PageOfCustomers> suggestNames(@RequestParam String name,
            @RequestParam(defaultValue = "0") Integer page) {
        System.out.println(page);
        return ResponseEntity.ok(customerService.searchNames(name, page));
    }

}
