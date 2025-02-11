package com.example.internship.controller;

import com.example.internship.dto.shelf.ShelfCreateRequest;
import com.example.internship.dto.shelf.ShelfCreateResponse;
import com.example.internship.dto.shelf.ShelfEditRequest;
import com.example.internship.dto.shelf.ShelfEditResponse;
import com.example.internship.dao.entity.Shelf;
import com.example.internship.facade.ShelfFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shelf")
@RequiredArgsConstructor
public class ShelfController {

    private final ShelfFacade shelfFacade;

    @GetMapping
    public Iterable<Shelf> findAllShelf() {
        return shelfFacade.findAll();
    }

    @PostMapping
    public ShelfCreateResponse addOneShelf(@RequestBody ShelfCreateRequest shelfCreateRequest){
        return shelfFacade.savePostRequest(shelfCreateRequest);
    }

    @PutMapping(value = "/{id}")
    public ShelfEditResponse replaceShelf(@RequestBody ShelfEditRequest shelfEditRequest,
                                          @PathVariable long id) {
        return shelfFacade.saveEditRequest(shelfEditRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteShelf(@PathVariable long id) {
        shelfFacade.delete(id);
    }
}
