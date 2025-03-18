package com.example.internship.dto.client;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClientEditResponse {

    private String firstName;
    private String middleName;
    private String userEmail;
}
