package com.example.internship.controller;

import com.example.internship.dto.shelf.*;
import com.example.internship.facade.ShelfFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shelf")
@RequiredArgsConstructor
public class ShelfController {

    private final ShelfFacade shelfFacade;

    @GetMapping(value = "/list")
    public ResponseEntity<List<String>> findAllShelfName() {
        return shelfFacade.findAllShelfName();
    }

    @GetMapping
    public Iterable<ShelfGetResponse> findAllShelf() {
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
