package com.example.internship.dto.shelf;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ShelfGetResponse {

    private String name;
    private Integer bookcaseNumber;
    private List<String> bookNameList;
}
