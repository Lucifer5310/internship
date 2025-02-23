package com.example.internship.dto.shelf;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ShelfGetResponse {

    private String name;
    private int bookcaseNumber;
    private List<String> bookNameList;
}
