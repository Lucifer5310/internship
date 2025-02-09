package com.example.internship.dao;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "client")
@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@SequenceGenerator(name = "client_seq", sequenceName = "client_seq", allocationSize = 1)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq")
    private long id;
    private String firstName;
    private String middleName;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Book> books;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = false)
    private Users user;

    public void removeBook(Book book) {
        books.remove(book);
        book.setClient(null);
    }

    public void removeUser() {
        this.user.setClient(null);
    }
}
