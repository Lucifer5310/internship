package com.example.internship.config;

import com.example.internship.dto.image.ImageData;
import com.example.internship.dto.image.ImageMetadata;
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

    @GetMapping(value = "/images/download/{filename:.+}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    ResponseEntity<byte[]> getImageByFilename(@PathVariable("filename") String filename);

    @GetMapping(value = "/images/{filename:.+}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    ResponseEntity<byte[]> getImageContentByFilename(@PathVariable("filename") String filename);

    @GetMapping(value = "/images/list")
    ResponseEntity<List<String>> getAllImageFilenames();

    @GetMapping("/images/metadata")
    ResponseEntity<List<ImageMetadata>> getAllImagesMetadata();

    @GetMapping("/images/all")
    ResponseEntity<List<ImageData>> getAllImages();

    @DeleteMapping("/images/delete/{filename:.+}")
    ResponseEntity<Void> deleteImageByFilename(@PathVariable("filename") String filename);
}
