package com.abdo.shop.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.abdo.shop.model.dto.request.EditItemRequest;
import com.abdo.shop.model.dto.response.ItemResponse;
import com.abdo.shop.model.entity.ItemEntity;
import com.abdo.shop.model.entity.KeyEntity;
// import com.abdo.shop.model.entity.PhotoEntity;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    @Mapping(source = "quantityInStock", target = "quantity")
    ItemResponse itemEntityItemResponse(ItemEntity item);

    @Mapping(target = "keys", ignore = true)
    @Mapping(target = "lastEdit", ignore = true)
    @Mapping(target = "photos", ignore = true)
    @Mapping(target = "quantityInStock", source = "quantity")
    ItemEntity editItemRequestItemEntity(EditItemRequest editItemRequest);

    default List<String> map(List<KeyEntity> keys) {
        return keys.stream().map(KeyEntity::getName).toList();
    }

    // default List<Long> mapPhotos(List<PhotoEntity> photoEntities) {
    // return photoEntities.stream().map(PhotoEntity::getId).toList();
    // }
}
