package com.example.internship.dao.repository;

import com.example.internship.dao.entity.Bookcase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookcaseRepository extends JpaRepository<Bookcase, Long> {

    Optional<Bookcase> findBookcaseByNumber(Integer number);
}
