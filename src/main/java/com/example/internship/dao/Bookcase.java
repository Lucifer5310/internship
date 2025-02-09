package com.example.internship.dao;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "bookcase")
@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@SequenceGenerator(name = "bookcase_seq", sequenceName = "bookcase_seq", allocationSize = 1)
public class Bookcase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookcase_seq")
    private long id;
    private int number;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bookcase", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Shelf> shelfs;

    public void removeShelf(Shelf shelf) {
        shelfs.remove(shelf);
        shelf.setBookcase(null);
    }
}
