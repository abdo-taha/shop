package com.abdo.shop.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.abdo.shop.model.entity.ItemEntity;

public interface PhotoService {
    String addPhoto(MultipartFile photo, ItemEntity item) throws IllegalStateException, IOException;

    void deletePhoto(Long id);

    String getURL(Long id);
}
