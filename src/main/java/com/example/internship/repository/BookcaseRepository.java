package com.example.internship.repository;

import com.example.internship.dao.Bookcase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookcaseRepository extends JpaRepository<Bookcase, Integer> {
}
