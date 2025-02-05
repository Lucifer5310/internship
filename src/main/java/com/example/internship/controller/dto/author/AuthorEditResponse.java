package com.example.internship.controller.dto.author;

import com.example.internship.dao.Book;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthorEditResponse {

    private String name;
    private long dateOfBirth;
}
