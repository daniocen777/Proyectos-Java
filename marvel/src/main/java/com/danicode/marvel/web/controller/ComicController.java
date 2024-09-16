package com.danicode.marvel.web.controller;

import com.danicode.marvel.dto.CustomPageable;
import com.danicode.marvel.persistence.integration.marvel.dto.ComicDto;
import com.danicode.marvel.service.IComicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comics")
public class ComicController {

    private final IComicService comicService;

    public ComicController(IComicService comicService) {
        this.comicService = comicService;
    }

    // Punto 2 y 4 de los requerimientos (ver imagen en resources)
    @GetMapping
    public ResponseEntity<List<ComicDto>> findAll(
            @RequestParam(required = false) Long characterId,
            @RequestParam(defaultValue = "0") long offset,
            @RequestParam(defaultValue = "10") long limit
    ) {

        CustomPageable pageable = new CustomPageable(offset, limit);

        return ResponseEntity.ok(comicService.findAll(pageable, characterId));
    }

    // Punto 5 de los requerimientos (ver imagen en resources)
    @GetMapping("/{comicId}")
    public ResponseEntity<ComicDto> findById(
            @PathVariable Long comicId
    ) {
        return ResponseEntity.ok(comicService.findById(comicId));
    }
}
