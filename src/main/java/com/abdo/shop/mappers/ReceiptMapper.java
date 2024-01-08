package com.abdo.shop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.abdo.shop.model.dto.response.ReceiptResponse;
import com.abdo.shop.model.entity.ReceiptEntity;

@Mapper(componentModel = "spring", uses = SoldItemMapper.class)
public interface ReceiptMapper {
    ReceiptMapper INSTANCE = Mappers.getMapper(ReceiptMapper.class);

    ReceiptResponse receiptEntityToReceiptResponse(ReceiptEntity receiptEntity);
}
