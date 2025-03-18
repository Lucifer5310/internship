package com.example.internship.dto.author;

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
