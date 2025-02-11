package com.example.internship.facade;

import com.example.internship.dto.shelf.ShelfCreateRequest;
import com.example.internship.dto.shelf.ShelfCreateResponse;
import com.example.internship.dto.shelf.ShelfEditRequest;
import com.example.internship.dto.shelf.ShelfEditResponse;
import com.example.internship.dao.entity.Shelf;
import com.example.internship.service.ShelfService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ShelfFacade {

    private final ShelfService shelfService;

    public Iterable<Shelf> findAll() {
        return shelfService.findAll();
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
}
