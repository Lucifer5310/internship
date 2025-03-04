package com.example.internship.facade;

import com.example.internship.dao.entity.Book;
import com.example.internship.dto.client.ClientGetResponse;
import com.example.internship.dto.shelf.*;
import com.example.internship.dao.entity.Shelf;
import com.example.internship.service.ShelfService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
@Slf4j
public class ShelfFacade {

    private final ShelfService shelfService;

    public Iterable<ShelfGetResponse> findAll() {
        return StreamSupport.stream(shelfService.findAll().spliterator(), false)
                .map(shelf -> new ShelfGetResponse(
                        shelf.getName(),
                        shelf.getBookcase().getNumber(),
                        shelf.getBooks().stream()
                                .map(Book::getName)
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public void delete(long id) {
        shelfService.delete(id);
    }

    public ShelfCreateResponse savePostRequest(ShelfCreateRequest shelfCreateRequest){
        final Shelf shelf = new Shelf();
        shelf.setName(shelfCreateRequest.getName());

        Shelf saved = shelfService.save(shelf);
        log.info("Shelf is added");

        return ShelfCreateResponse.builder()
                .name(saved.getName())
                .build();
    }

    public ShelfEditResponse saveEditRequest(ShelfEditRequest shelfEditRequest, long id){
        Shelf shelf = shelfService.findById(id);
        shelf.setName(shelfEditRequest.getName());

        Shelf saved = shelfService.save(shelf);
        log.info("Shelf is edited");

        return ShelfEditResponse.builder()
                .name(saved.getName())
                .build();
    }

    public ResponseEntity<List<String>> findAllShelfName() {
        List<String> names = shelfService.findAllShelfName();
        return ResponseEntity.ok(names);
    }
}
