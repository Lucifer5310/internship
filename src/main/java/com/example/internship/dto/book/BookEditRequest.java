package com.example.internship.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookEditRequest {

    private String name;
    private String genre;
    private String authorName;
    private String imageName;
    private String shelfName;
    private Long clientId;
    private boolean isRead;
}
