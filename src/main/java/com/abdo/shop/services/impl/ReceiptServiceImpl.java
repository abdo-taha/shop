package com.abdo.shop.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.abdo.shop.exceptions.NotFoundException;
import com.abdo.shop.mappers.ReceiptMapper;
import com.abdo.shop.model.dto.request.NewReceiptRequest;
import com.abdo.shop.model.dto.response.PageOfReceipts;
import com.abdo.shop.model.dto.response.ReceiptResponse;
import com.abdo.shop.model.entity.CustomerEntity;
import com.abdo.shop.model.entity.ItemEntity;
import com.abdo.shop.model.entity.ReceiptEntity;
import com.abdo.shop.model.entity.SoldItemEntity;
import com.abdo.shop.repositories.ReceiptRepository;
import com.abdo.shop.services.ItemService;
import com.abdo.shop.services.ReceiptService;
import com.abdo.shop.services.SoldItemService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final SoldItemService soldItemService;
    private final ItemService itemService;
    private final ReceiptMapper receiptMapper;

    @Transactional
    @Override
    public ReceiptResponse createReceipt(NewReceiptRequest newReceiptRequest, CustomerEntity customerEntity) {
        ReceiptEntity receiptEntity = ReceiptEntity.builder().lastEdited(LocalDateTime.now()).total(0.0)
                .customer(customerEntity)
                .build();
        ReceiptEntity savedReceipt = receiptRepository.save(receiptEntity);
        final Double[] total = { 0.0 };
        List<SoldItemEntity> soldItems = new ArrayList<SoldItemEntity>();
        newReceiptRequest.items().forEach(item -> {
            ItemEntity itemEntity = itemService.getItemEntityById(item.itemId());
            soldItems.add(soldItemService.addItem(item.quantity(), itemEntity, savedReceipt));
            total[0] += item.quantity() * itemEntity.getPrice();
            itemEntity.setQuantityInStock(itemEntity.getQuantityInStock() - item.quantity());
        });
        savedReceipt.setTotal(total[0]);
        savedReceipt.setItems(soldItems);
        ReceiptEntity receipt = receiptRepository.save(savedReceipt);
        return receiptMapper.receiptEntityToReceiptResponse(receipt);
    }

    @Override
    public ReceiptResponse getReceipt(Long id) {
        ReceiptEntity receiptEntity = receiptRepository.findById(id).orElseThrow(() -> new NotFoundException());
        return receiptMapper.receiptEntityToReceiptResponse(receiptEntity);
    }

    @Override
    public PageOfReceipts getReceipts(Integer page) {
        Page<ReceiptEntity> receipts = receiptRepository.findAll(PageRequest.of(page, 10));
        PageOfReceipts receiptsPage = PageOfReceipts.builder()
                .hasNext(receipts.hasNext())
                .receipts(receipts.getContent().stream().map(receiptMapper::receiptEntityToReceiptResponse).toList())
                .build();
        return receiptsPage;
    }

    @Override
    public void deleteReceipt(Long id) {
        receiptRepository.deleteById(id);
    }

    @Override
    public PageOfReceipts getReceiptsByCustomer(CustomerEntity customerEntity, Integer page) {
        Page<ReceiptEntity> receipts = receiptRepository.findByCustomer(customerEntity, PageRequest.of(page, 10));
        PageOfReceipts pageOfReceipts = PageOfReceipts.builder()
                .hasNext(receipts.hasNext())
                .receipts(receipts.stream()
                        .map(receiptMapper::receiptEntityToReceiptResponse).toList())
                .build();
        return pageOfReceipts;
    }

}
