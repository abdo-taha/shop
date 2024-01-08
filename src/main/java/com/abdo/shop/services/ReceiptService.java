package com.abdo.shop.services;

import com.abdo.shop.model.dto.request.NewReceiptRequest;

public interface ReceiptService {
    void createReceipt(NewReceiptRequest newReceiptRequest);
    // TODO edit and delete
}
