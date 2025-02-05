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
public class Shelf {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shelf", cascade = CascadeType.ALL)
    private List<Book> books;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookcase_id")
    private Bookcase bookcase;
}
