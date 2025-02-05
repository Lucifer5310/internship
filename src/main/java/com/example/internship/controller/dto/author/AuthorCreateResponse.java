package com.example.internship.controller.dto.author;

import com.example.internship.dao.Book;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthorCreateResponse {

    private String name;
    private long dateOfBirth;
}
