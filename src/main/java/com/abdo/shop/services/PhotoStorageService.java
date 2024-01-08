package com.abdo.shop.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoStorageService {
    String save(MultipartFile photo, Long id) throws IllegalStateException, IOException;

    String getURL(Long id);

    Boolean deletePhoto(Long id);
}
