package com.example.internship.facade;

import com.example.internship.controller.dto.shelf.ShelfCreateRequest;
import com.example.internship.controller.dto.shelf.ShelfCreateResponse;
import com.example.internship.controller.dto.shelf.ShelfEditRequest;
import com.example.internship.controller.dto.shelf.ShelfEditResponse;
import com.example.internship.dao.Shelf;
import com.example.internship.service.ShelfService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
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

        return ShelfCreateResponse.builder()
                .name(saved.getName())
                .build();
    }

    public ShelfEditResponse saveEditRequest(ShelfEditRequest shelfEditRequest, long id){
        Shelf shelf = shelfService.findById(id);
        shelf.setName(shelfEditRequest.getName());

        Shelf saved = shelfService.save(shelf);

        return ShelfEditResponse.builder()
                .name(saved.getName())
                .build();
    }
}
