package com.abdo.shop.services.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.abdo.shop.model.entity.ItemEntity;
import com.abdo.shop.model.entity.PhotoEntity;
import com.abdo.shop.repositories.PhotoRepository;
import com.abdo.shop.services.PhotoService;
import com.abdo.shop.services.PhotoStorageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PhotoServiceImpl implements PhotoService {

    final private PhotoRepository photoRepository;
    final private PhotoStorageService photoStorageService;

    @Override
    public String addPhoto(MultipartFile photo, ItemEntity item) throws IllegalStateException, IOException {
        // TODO more extension control (not important for me)
        PhotoEntity photoDB = PhotoEntity.builder().item(item).ext("jpg").build();
        PhotoEntity savedPhoto = photoRepository.save(photoDB);

        photoStorageService.save(photo, savedPhoto.getId());

        return getURL(savedPhoto.getId());
    }

    @Override
    public void deletePhoto(Long id) {
        photoStorageService.deletePhoto(id);
        photoRepository.deleteById(id);
    }

    @Override
    public String getURL(Long id) {
        return photoStorageService.getURL(id);
    }

}
