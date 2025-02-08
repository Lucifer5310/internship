package com.example.internship.controller.dto.book;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookEditResponse {

    private String name;
    private String genre;
    private boolean isRead;
}
