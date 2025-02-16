package com.example.internship.dao.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


// Better to use annotation @Column for every field and with help of this annotation add additional validation for dao layer and name columns.
// This comment for every entity
@Entity
@Table(name = "author")
@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@SequenceGenerator(name = "author_seq", sequenceName = "author_seq", allocationSize = 1)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_seq")
    @Column(name = "id")
    private long id;
    private String name;
    private long dateOfBirth; // Why not LocalDate?

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Book> books;
}
