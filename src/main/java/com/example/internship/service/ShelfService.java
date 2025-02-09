package com.example.internship.service;

import com.example.internship.dao.Book;
import com.example.internship.dao.Client;
import com.example.internship.dao.Shelf;
import com.example.internship.repository.ShelfRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShelfService {

    private final ShelfRepository shelfRepository;

    @Transactional
    public void delete(Long id) {
        Optional<Shelf> shelfOptional = shelfRepository.findById(id);

        if (shelfOptional.isPresent()) {
            Shelf shelf = shelfOptional.get();

            for (Book book : shelf.getBooks()) {
                book.setShelf(null);
            }
            shelf.getBooks().clear();

            if (shelf.getBookcase() != null) {
                shelf.getBookcase().getShelfs().remove(shelf);
                shelf.setBookcase(null);
            }

            shelfRepository.save(shelf);

            shelfRepository.deleteById(id);
        } else {
            throw new RuntimeException("Клиент с ID " + id + " не найден");
        }
    }

    public Shelf save(Shelf shelf){
        return shelfRepository.save(shelf);
    }

    public Iterable<Shelf> findAll() {
        return shelfRepository.findAll();
    }

    public Shelf findById(long id) {
        Optional<Shelf> byId = shelfRepository.findById(id);
        return byId.orElseThrow();
    }
}
