package com.danicode.marvel.service;

import com.danicode.marvel.dto.CustomPageable;
import com.danicode.marvel.persistence.integration.marvel.dto.CharacterDto;

import java.util.List;

public interface ICharacterService {
    List<CharacterDto> findAll(CustomPageable pageable, String name, int[] comics, int[] series);

    CharacterDto.CharacterInfoDto findInfoById(Long characterId);
}
