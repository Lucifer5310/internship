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
public class Bookcase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int number;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bookcase", cascade = CascadeType.ALL)
    private List<Shelf> shelfs;
}
