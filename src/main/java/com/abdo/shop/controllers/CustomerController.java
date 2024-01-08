package com.abdo.shop.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdo.shop.model.dto.request.EditCustomerRequest;
import com.abdo.shop.model.dto.response.CustomerResponse;
import com.abdo.shop.services.CustomerService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CustomerResponse>> getAll() {
        return ResponseEntity.ok(customerService.getAll());
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

    @GetMapping("/suggest")
    public ResponseEntity<List<CustomerResponse>> suggestNames(@RequestParam String name) {
        System.out.println(name);
        return ResponseEntity.ok(customerService.searchNames(name));
    }

}
