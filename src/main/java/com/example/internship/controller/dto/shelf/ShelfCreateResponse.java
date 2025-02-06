package com.example.internship.controller.dto.shelf;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShelfCreateResponse {

    private String name;
    private int bookcase_id;
}
