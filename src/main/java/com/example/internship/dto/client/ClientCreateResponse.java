package com.example.internship.dto.client;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClientCreateResponse {

    private String firstName;
    private String middleName;
}
