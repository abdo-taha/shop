package com.abdo.shop.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.text.html.Option;

import org.springframework.stereotype.Service;

import com.abdo.shop.model.dto.request.EditCustomerRequest;
import com.abdo.shop.model.dto.response.CustomerResponse;
import com.abdo.shop.model.entity.CustomerEntity;
import com.abdo.shop.repositories.CustomerRepository;
import com.abdo.shop.services.CustomerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    final private CustomerRepository customerRepository;

    @Override
    public CustomerResponse createCustomer(String name) {

        System.out.println(name);
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
        // TODO throw
        CustomerEntity customerEntity = customerRepository.findById(editCustomerRequest.id()).orElseThrow();
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
    public List<CustomerResponse> searchNames(String name) {
        // TODO pagination
        List<CustomerEntity> customers = customerRepository.findByNameStartsWith(name);
        return customers.stream()
                .map((customer) -> CustomerResponse.builder().id(customer.getId()).name(customer.getName()).build())
                .collect(Collectors.toList());
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
    public List<CustomerResponse> getAll() {
        return customerRepository.findAll().stream()
                .map((customer) -> CustomerResponse.builder().id(customer.getId()).name(customer.getName()).build())
                .toList();
    }

    @Override
    public CustomerResponse getCustomer(Long id) {
        // TODO throw
        CustomerEntity customer = customerRepository.findById(id).orElseThrow();
        return CustomerResponse.builder().id(customer.getId()).name(customer.getName()).build();
    }

}
