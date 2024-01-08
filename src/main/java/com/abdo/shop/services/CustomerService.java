package com.abdo.shop.services;

import com.abdo.shop.model.dto.request.EditCustomerRequest;
import com.abdo.shop.model.dto.request.NewReceiptRequest;
import com.abdo.shop.model.dto.response.CustomerResponse;
import com.abdo.shop.model.dto.response.PageOfCustomers;
import com.abdo.shop.model.dto.response.PageOfReceipts;
import com.abdo.shop.model.dto.response.ReceiptResponse;
import com.abdo.shop.model.entity.CustomerEntity;

public interface CustomerService {

    public CustomerResponse createCustomer(String name);

    public CustomerEntity getOrCreateCustomer(String name);

    public CustomerResponse EditCustomerName(EditCustomerRequest editCustomerRequest);

    public void deleteCustomer(Long id);

    public PageOfCustomers searchNames(String name, Integer page);

    public PageOfCustomers getAll(Integer page);

    public CustomerResponse getCustomer(Long id);

    public ReceiptResponse createReceipt(NewReceiptRequest newReceiptRequest);

    PageOfReceipts getReceiptsByCustomer(Long id, Integer page);

}
