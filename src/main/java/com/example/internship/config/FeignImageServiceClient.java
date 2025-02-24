package com.example.internship.config;

import com.example.internship.dto.ImageMetadata;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "image-service", url = "http://imageservice:8081", configuration = FeignMultipartConfig.class)
public interface FeignImageServiceClient {

    @PostMapping(value = "/images/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<String> uploadImage(@RequestPart("file") MultipartFile file);

    @GetMapping(value = "/images/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    ResponseEntity<byte[]> getImage(@PathVariable("id") String id);

    @GetMapping("/images")
    ResponseEntity<List<ImageMetadata>> getAllImagesMetadata();

    @DeleteMapping("/images/{id}")
    ResponseEntity<Void> deleteImage(@PathVariable String id);
}
