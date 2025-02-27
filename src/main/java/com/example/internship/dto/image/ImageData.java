package com.example.internship.dto.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ImageData {

    @JsonProperty("id")
    private String id;

    @JsonProperty("filename")
    private String filename;

    @JsonProperty("uploadDate")
    private Date uploadDate;

    @JsonProperty("length")
    private long length;

    @JsonProperty("contentType")
    private String contentType;

    @JsonProperty("content")
    private byte[] content;
}