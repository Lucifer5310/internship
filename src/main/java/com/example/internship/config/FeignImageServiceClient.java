package com.example.internship.config;

import com.example.internship.dto.ImageMetadata;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "image-service", url = "http://localhost:8081")
public interface FeignImageServiceClient {

    @PostMapping("/images/upload")
    ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file);

    @GetMapping("/images/{id}")
    ResponseEntity<Resource> getImage(@PathVariable String id);

    @GetMapping("/images")
    ResponseEntity<List<ImageMetadata>> getAllImagesMetadata();

    @DeleteMapping("/images/{id}")
    ResponseEntity<Void> deleteImage(@PathVariable String id);
}
