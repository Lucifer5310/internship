package com.example.internship.dto.shelf;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShelfCreateRequest {

    private String name;
    private Integer bookcaseNumber;
}
