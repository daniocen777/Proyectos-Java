package com.danicode.marvel.web.controller;

import com.danicode.marvel.dto.CustomPageable;
import com.danicode.marvel.persistence.integration.marvel.dto.CharacterDto;
import com.danicode.marvel.service.ICharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    private final ICharacterService characterService;

    public CharacterController(ICharacterService characterService) {
        this.characterService = characterService;
    }

    // Punto 1 de los requerimientos (ver imagen en resources)
    @GetMapping
    public ResponseEntity<List<CharacterDto>> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) int[] comics,
            @RequestParam(required = false) int[] series,
            @RequestParam(defaultValue = "0") long offset,
            @RequestParam(defaultValue = "10") long limit) {

        CustomPageable pageable = new CustomPageable(offset, limit);

        return ResponseEntity.ok(characterService.findAll(pageable, name, comics, series));
    }

    // Punto 3 de los requerimientos (ver imagen en resources)
    @GetMapping("/{characterId}")
    public ResponseEntity<CharacterDto.CharacterInfoDto> findInfoById(@PathVariable Long characterId) {
        return ResponseEntity.ok(characterService.findInfoById(characterId));
    }
}
