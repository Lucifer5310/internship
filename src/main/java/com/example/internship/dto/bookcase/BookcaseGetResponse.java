package com.example.internship.dto.bookcase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class BookcaseGetResponse {

    private int number;
    private List<String> shelfName;
}
