package com.example.internship.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book")
@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@SequenceGenerator(name = "book_seq", sequenceName = "book_seq", allocationSize = 1)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    private long id;
    private String name;
    private String genre;
    private String imageName;
    private boolean isRead;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "shelf_id")
    private Shelf shelf;
}
