package com.abdo.shop.services;

import java.util.List;

import com.abdo.shop.model.dto.request.EditCustomerRequest;
import com.abdo.shop.model.dto.response.CustomerResponse;
import com.abdo.shop.model.entity.CustomerEntity;

public interface CustomerService {

    public CustomerResponse createCustomer(String name);

    public CustomerEntity getOrCreateCustomer(String name);

    public CustomerResponse EditCustomerName(EditCustomerRequest editCustomerRequest);

    public void deleteCustomer(Long id);

    public List<CustomerResponse> searchNames(String name);

    public List<CustomerResponse> getAll();

    public CustomerResponse getCustomer(Long id);

}
