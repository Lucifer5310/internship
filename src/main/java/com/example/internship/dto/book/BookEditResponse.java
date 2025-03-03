package com.example.internship.dto.book;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookEditResponse {

    private String name;
    private String genre;
    private String imageName;
    private boolean isRead;
    private Long shelfId;
    private Long clientId;
    private Long authorId;
}
