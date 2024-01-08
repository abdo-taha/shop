package com.abdo.shop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.abdo.shop.model.entity.KeyEntity;

@Mapper(componentModel = "spring")
public interface KeyMapper {
    KeyMapper INSTANCE = Mappers.getMapper(KeyMapper.class);

    String keyEntityString(KeyEntity key);
}
