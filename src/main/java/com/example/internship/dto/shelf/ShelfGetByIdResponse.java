package com.example.internship.dto.shelf;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ShelfGetByIdResponse {

    private String name;
    private Integer bookcaseNumber;
}
