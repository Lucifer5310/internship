package com.example.internship.service;

import com.example.internship.config.FeignImageServiceClient;
import com.example.internship.dto.image.ImageData;
import com.example.internship.dto.image.ImageMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
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

    // Изменённый метод: скачивание по имени файла
    public Resource downloadImageByFilename(String filename) {
        ResponseEntity<byte[]> response = imageServiceClient.getImageByFilename(filename);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return new ByteArrayResource(response.getBody());
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

    // Новый метод: получение всех картинок с содержимым
    public List<ImageData> getAllImages() {
        ResponseEntity<List<ImageData>> response = imageServiceClient.getAllImages();
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        throw new RuntimeException("Ошибка получения всех изображений");
    }

    // Изменённый метод: удаление по имени файла
    public void deleteImageByFilename(String filename) {
        ResponseEntity<Void> response = imageServiceClient.deleteImageByFilename(filename);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Ошибка удаления изображения");
        }
    }
}