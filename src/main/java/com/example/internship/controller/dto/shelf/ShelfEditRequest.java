package com.example.internship.controller.dto.shelf;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShelfEditRequest {

    private String name;
    private int bookcase_id;
}
