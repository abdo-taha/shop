package com.abdo.shop.services.impl;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.abdo.shop.config.SecretsConfigProperties;
import com.abdo.shop.repositories.PhotoRepository;
import com.abdo.shop.services.PhotoStorageService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PhotoStorageLocalImpl implements PhotoStorageService {

    private final SecretsConfigProperties secretsConfigProperties;
    private final PhotoRepository photoRepository;
    private final Environment environment;

    String localImagesPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\";
    String globalImagePath = "localhost:8080/data/images/";

    @PostConstruct
    private void addSecrets() {
        if (secretsConfigProperties.localImagePath() != null)
            localImagesPath = secretsConfigProperties.localImagePath();
        if (secretsConfigProperties.globalImageRelativePath() != null && secretsConfigProperties.publicUrl() != null)
            globalImagePath = secretsConfigProperties.publicUrl() + ":8080"
                    + secretsConfigProperties.globalImageRelativePath();

        try {
            globalImagePath = InetAddress.getLocalHost().getHostAddress() + ":" +
                    environment.getProperty("local.server.port")
                    + secretsConfigProperties.globalImageRelativePath();
        } catch (Exception e) {
        }

    }

    @Override
    public String save(MultipartFile photo, Long id) throws IllegalStateException, IOException {
        File directory = new File(localImagesPath);
        if (!directory.exists())
            directory.mkdirs();
        String path = localImagesPath + Long.toString(id) + '.' + getPhotoExt(id);
        photo.transferTo(new File(path));
        return getURL(id);
    }

    @Override
    public String getURL(Long id) {
        addSecrets();
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
