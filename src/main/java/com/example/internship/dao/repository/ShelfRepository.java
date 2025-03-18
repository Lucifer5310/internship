package com.example.internship.dao.repository;

import com.example.internship.dao.entity.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShelfRepository extends JpaRepository<Shelf, Long> {

    Optional<Shelf> findShelfByName(String name);
}
