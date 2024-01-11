package com.abdo.shop.services.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.abdo.shop.exceptions.NotFoundException;
import com.abdo.shop.model.dto.request.EditCustomerRequest;
import com.abdo.shop.model.dto.request.NewReceiptRequest;
import com.abdo.shop.model.dto.response.CustomerResponse;
import com.abdo.shop.model.dto.response.PageOfCustomers;
import com.abdo.shop.model.dto.response.PageOfReceipts;
import com.abdo.shop.model.dto.response.ReceiptResponse;
import com.abdo.shop.model.entity.CustomerEntity;
import com.abdo.shop.repositories.CustomerRepository;
import com.abdo.shop.services.CustomerService;
import com.abdo.shop.services.ReceiptService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    final private CustomerRepository customerRepository;
    final private ReceiptService receiptService;

    @Override
    public CustomerResponse createCustomer(String name) {

        // System.out.println(name);
        Optional<CustomerEntity> savedCustomerCheck = customerRepository.findByName(name);
        CustomerEntity savedCustomer;
        if (!savedCustomerCheck.isPresent()) {
            CustomerEntity customer = CustomerEntity.builder()
                    .name(name)
                    .build();
            savedCustomer = customerRepository.save(customer);

        } else
            savedCustomer = savedCustomerCheck.get();
        CustomerResponse customerResponse = CustomerResponse.builder().name(savedCustomer.getName())
                .id(savedCustomer.getId()).build();
        return customerResponse;
    }

    @Override
    public CustomerResponse EditCustomerName(EditCustomerRequest editCustomerRequest) {

        CustomerEntity customerEntity = customerRepository.findById(editCustomerRequest.id())
                .orElseThrow(() -> new NotFoundException());
        customerEntity.setName(editCustomerRequest.name());
        CustomerEntity savedCustomer = customerRepository.save(customerEntity);
        CustomerResponse customerResponse = CustomerResponse.builder().name(savedCustomer.getName())
                .id(savedCustomer.getId()).build();
        return customerResponse;
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public PageOfCustomers searchNames(String name, Integer page) {
        Page<CustomerEntity> customers = customerRepository.findByNameStartsWith(name, PageRequest.of(page, 10));

        PageOfCustomers pageOfCustomers = PageOfCustomers.builder().hasNext(customers.hasNext())
                .customers(customers.stream()
                        .map((customer) -> CustomerResponse.builder().id(customer.getId()).name(customer.getName())
                                .build())
                        .collect(Collectors.toList()))
                .build();
        return pageOfCustomers;
    }

    @Override
    public CustomerEntity getOrCreateCustomer(String name) {
        CustomerEntity savedCustomer = customerRepository.findByName(name).orElse(null);
        if (savedCustomer == null) {
            CustomerEntity customer = CustomerEntity.builder()
                    .name(name)
                    .build();
            savedCustomer = customerRepository.save(customer);

        }
        return savedCustomer;
    }

    @Override
    public PageOfCustomers getAll(Integer page) {
        Page<CustomerEntity> customers = customerRepository.findAll(PageRequest.of(page, 10));
        PageOfCustomers customerPage = PageOfCustomers.builder().hasNext(customers.hasNext()).customers(customers
                .getContent().stream()
                .map((customer) -> CustomerResponse.builder().id(customer.getId()).name(customer.getName()).build())
                .toList()).build();
        return customerPage;
    }

    @Override
    public CustomerResponse getCustomer(Long id) {

        CustomerEntity customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException());
        return CustomerResponse.builder().id(customer.getId()).name(customer.getName()).build();
    }

    @Override
    public ReceiptResponse createReceipt(NewReceiptRequest newReceiptRequest) {
        CustomerEntity customerEntity = getOrCreateCustomer(newReceiptRequest.customer());
        return receiptService.createReceipt(newReceiptRequest, customerEntity);
    }

    @Override
    public PageOfReceipts getReceiptsByCustomer(@NonNull Long id, Integer page) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(() -> new NotFoundException());
        return receiptService.getReceiptsByCustomer(customerEntity, page);
    }

}
