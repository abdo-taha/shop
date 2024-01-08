package com.abdo.shop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.abdo.shop.model.dto.response.SoldItemResponse;
import com.abdo.shop.model.entity.ItemEntity;
import com.abdo.shop.model.entity.SoldItemEntity;

@Mapper(componentModel = "spring")
public interface SoldItemMapper {
    SoldItemMapper INSTANCE = Mappers.getMapper(SoldItemMapper.class);

    @Mapping(target = "itemId", source = "item")
    @Mapping(target = "itemName", source = "item")
    SoldItemResponse soldItemToSoldItemResponse(SoldItemEntity soldItemEntity);

    default Long mapId(ItemEntity itemEntity) {
        return itemEntity.getId();
    }

    default String mapName(ItemEntity itemEntity) {
        return itemEntity.getName();
    }
}
