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

    private Long id;
    private String name;
    private String genre;
    private String authorName;
    private String shelfName;
    private int bookcaseNumber;
    private String imageName;
    private boolean isRead;
}
