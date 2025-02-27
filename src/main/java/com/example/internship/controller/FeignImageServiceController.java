package com.example.internship.controller;

import com.example.internship.dto.image.ImageData;
import com.example.internship.dto.image.ImageMetadata;
import com.example.internship.service.FeignImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class FeignImageServiceController {

    private final FeignImageService feignImageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestPart("file") MultipartFile file) {
        try {
            String imageId = feignImageService.uploadImage(file);
            return ResponseEntity.ok(imageId);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // Изменённый эндпоинт: скачивание по имени файла
    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> getImageByFilename(@PathVariable String filename) {
        try {
            Resource imageResource = feignImageService.downloadImageByFilename(filename);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Можно сделать динамическим, если передать contentType
                    .body(imageResource);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/metadata")
    public ResponseEntity<List<ImageMetadata>> getAllImagesMetadata() {
        try {
            List<ImageMetadata> metadataList = feignImageService.getAllImagesMetadata();
            return ResponseEntity.ok(metadataList);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Новый эндпоинт: получение всех картинок
    @GetMapping("/all")
    public ResponseEntity<List<ImageData>> getAllImages() {
        try {
            List<ImageData> images = feignImageService.getAllImages();
            return ResponseEntity.ok(images);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Изменённый эндпоинт: удаление по имени файла
    @DeleteMapping("/delete/{filename:.+}")
    public ResponseEntity<Void> deleteImageByFilename(@PathVariable String filename) {
        try {
            feignImageService.deleteImageByFilename(filename);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}