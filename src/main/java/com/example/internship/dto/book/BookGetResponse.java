package com.example.internship.dto.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class BookGetResponse {

    private String name;
    private String genre;
    private String authorName;
    private String shelfName;
    private String imageName;
    private int bookcaseNumber;
    private boolean isRead;
}
