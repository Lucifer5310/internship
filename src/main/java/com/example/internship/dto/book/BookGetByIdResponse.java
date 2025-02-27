package com.example.internship.dto.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class BookGetByIdResponse {

    private String name;
    private String genre;
    private String imageName;
    private boolean isRead;
    private Long shelfId;
    private Long clientId;
    private Long authorId;
}
