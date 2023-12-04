package com.abdo.shop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.abdo.shop.model.dto.ItemDto;
import com.abdo.shop.model.entity.ItemEntity;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);
    @Mapping(source = "quantityInStock",target = "quantity")
    ItemDto itemEntityTOItemDto(ItemEntity item);
    
    @Mapping(source = "quantity",target = "quantityInStock")
    ItemEntity itemDtoToItemEntity(ItemDto item);
}
