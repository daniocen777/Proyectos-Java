package com.danicode.marvel.service.impl;

import com.danicode.marvel.dto.CustomPageable;
import com.danicode.marvel.persistence.integration.marvel.dto.CharacterDto;
import com.danicode.marvel.persistence.integration.marvel.repository.CharacterRepository;
import com.danicode.marvel.service.ICharacterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl implements ICharacterService {

    private final CharacterRepository repository;

    public CharacterServiceImpl(CharacterRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CharacterDto> findAll(CustomPageable pageable, String name, int[] comics, int[] series) {
        return repository.findAll(pageable, name, comics, series);
    }

    @Override
    public CharacterDto.CharacterInfoDto findInfoById(Long characterId) {
        return repository.findInfoById(characterId);
    }
}
