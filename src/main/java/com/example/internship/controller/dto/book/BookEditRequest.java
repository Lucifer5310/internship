package com.example.internship.controller.dto.book;

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
    private boolean isRead;
    private long author_id;
    private long client_id;
    private long shelf_id;
}
