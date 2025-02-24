package com.example.internship.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ImageMetadata {

    private String id;
    private String filename;
    private Date uploadDate;
    private long length;
}