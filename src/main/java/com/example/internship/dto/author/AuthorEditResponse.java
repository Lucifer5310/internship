package com.example.internship.dto.author;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthorEditResponse {

    private String name;
    private long dateOfBirth;
}
