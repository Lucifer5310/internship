package com.example.internship.dto.author;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AuthorGetResponse {

    private String name;
    private long dateOfBirth;
}
