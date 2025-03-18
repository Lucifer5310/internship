package com.example.internship.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookCreateRequest {

    private String name;
    private String genre;
    private String imageName;
    private boolean isRead;
    private String authorName;
    private String shelfName;
}
