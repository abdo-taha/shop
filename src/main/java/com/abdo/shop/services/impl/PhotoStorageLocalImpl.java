package com.abdo.shop.services.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.abdo.shop.repositories.PhotoRepository;
import com.abdo.shop.services.PhotoStorageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PhotoStorageLocalImpl implements PhotoStorageService {
    // TODO edit local path and make it in properties
    // TODO edit global path get it from somewhere

    final private PhotoRepository photoRepository;
    String localImagesPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\";
    String globalImagePath = "localhost:8080/data/images/";

    @Override
    public String save(MultipartFile photo, Long id) throws IllegalStateException, IOException {
        // TODO photo check extenstion and size
        File directory = new File(localImagesPath);
        if (!directory.exists())
            directory.mkdirs();
        String path = localImagesPath + Long.toString(id) + '.' + getPhotoExt(id);
        photo.transferTo(new File(path));
        return getURL(id);
    }

    @Override
    public String getURL(Long id) {
        return globalImagePath + Long.toString(id) + "." + getPhotoExt(id);
    }

    @Override
    public Boolean deletePhoto(Long id) {
        String path = localImagesPath + Long.toString(id) + "." + getPhotoExt(id);
        File file = new File(path);
        return file.delete();
    }

    private String getPhotoExt(Long id) {

        return photoRepository.findById(id).orElseThrow().getExt();
    }

}
