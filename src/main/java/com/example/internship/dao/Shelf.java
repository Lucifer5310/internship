package com.example.internship.dao;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "shelf")
@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@SequenceGenerator(name = "shelf_seq", sequenceName = "shelf_seq", allocationSize = 1)
public class Shelf {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shelf_seq")
    private long id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shelf", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Book> books;

    @ManyToOne
    @JoinColumn(name = "bookcase_id")
    private Bookcase bookcase;
}
