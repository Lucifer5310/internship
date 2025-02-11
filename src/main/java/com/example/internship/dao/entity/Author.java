package com.example.internship.dao.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    private long dateOfBirth;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Book> books;
}
