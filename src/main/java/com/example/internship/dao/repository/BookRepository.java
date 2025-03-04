package com.example.internship.dao.repository;

import com.example.internship.dao.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookByName(String name);
}
