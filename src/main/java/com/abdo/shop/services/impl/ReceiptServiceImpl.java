package com.abdo.shop.services.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.abdo.shop.model.dto.request.NewReceiptRequest;
import com.abdo.shop.model.entity.CustomerEntity;
import com.abdo.shop.model.entity.ItemEntity;
import com.abdo.shop.model.entity.ReceiptEntity;
import com.abdo.shop.repositories.ReceiptRepository;
import com.abdo.shop.services.CustomerService;
import com.abdo.shop.services.ItemService;
import com.abdo.shop.services.ReceiptService;
import com.abdo.shop.services.SoldItemService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final CustomerService customerService;
    private final ReceiptRepository receiptRepository;
    private final SoldItemService soldItemService;
    private final ItemService itemService;

    @Override
    public void createReceipt(NewReceiptRequest newReceiptRequest) {
        // TODO transaction
        CustomerEntity customerEntity = customerService.getOrCreateCustomer(newReceiptRequest.customer());
        ReceiptEntity receiptEntity = ReceiptEntity.builder().lastEdited(LocalDateTime.now()).customer(customerEntity)
                .build();
        ReceiptEntity savedReceipt = receiptRepository.save(receiptEntity);
        final Double[] total = { 0.0 };
        newReceiptRequest.items().forEach(item -> {
            ItemEntity itemEntity = itemService.getItemEntityById(item.itemId());
            soldItemService.addItem(item.quantity(), itemEntity, savedReceipt);
            total[0] += item.quantity() * itemEntity.getPrice();
        });
        savedReceipt.setTotal(total[0]);
        receiptRepository.save(savedReceipt);

    }

}
