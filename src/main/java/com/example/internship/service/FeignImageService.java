package com.example.internship.service;

import com.example.internship.config.FeignImageServiceClient;
import com.example.internship.dto.ImageMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeignImageService {

    private final FeignImageServiceClient imageServiceClient;

    public String uploadImage(MultipartFile file) {
        ResponseEntity<String> response = imageServiceClient.uploadImage(file);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        throw new RuntimeException("Ошибка загрузки изображения");
    }

    public Resource downloadImage(String id) {
        ResponseEntity<Resource> response = imageServiceClient.getImage(id);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        throw new RuntimeException("Ошибка получения изображения");
    }

    public List<ImageMetadata> getAllImagesMetadata() {
        ResponseEntity<List<ImageMetadata>> response = imageServiceClient.getAllImagesMetadata();
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        throw new RuntimeException("Ошибка получения метаданных изображений");
    }

    public void deleteImage(String id) {
        ResponseEntity<Void> response = imageServiceClient.deleteImage(id);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Ошибка удаления изображения");
        }
    }
}
