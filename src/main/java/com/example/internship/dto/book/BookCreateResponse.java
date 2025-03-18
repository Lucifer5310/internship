package com.example.internship.dto.book;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookCreateResponse {

    private String name;
    private String genre;
    private boolean isRead;
}
