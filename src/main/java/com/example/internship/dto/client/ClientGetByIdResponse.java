package com.example.internship.dto.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ClientGetByIdResponse {

    private String firstName;
    private String middleName;
    private String userEmail;
    private String userName;
    private List<String> bookNameList;
}
