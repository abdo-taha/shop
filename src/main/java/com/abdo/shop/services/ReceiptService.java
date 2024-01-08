package com.abdo.shop.services;

import com.abdo.shop.model.dto.request.NewReceiptRequest;
import com.abdo.shop.model.dto.response.PageOfReceipts;
import com.abdo.shop.model.dto.response.ReceiptResponse;
import com.abdo.shop.model.entity.CustomerEntity;

public interface ReceiptService {
    ReceiptResponse createReceipt(NewReceiptRequest newReceiptRequest, CustomerEntity customerEntity);

    ReceiptResponse getReceipt(Long id);

    // List<ReceiptResponse> getReceiptsOfCustomer(String name);

    PageOfReceipts getReceipts(Integer page);

    PageOfReceipts getReceiptsByCustomer(CustomerEntity customerEntity, Integer page);

    void deleteReceipt(Long id);

    // TODO edit
}
