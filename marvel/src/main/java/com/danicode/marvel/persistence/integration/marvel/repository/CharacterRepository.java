package com.danicode.marvel.persistence.integration.marvel.repository;

import com.danicode.marvel.dto.CustomPageable;
import com.danicode.marvel.persistence.integration.marvel.dto.CharacterDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CharacterRepository {
    public List<CharacterDto> findAll(CustomPageable pageable, String name, int[] comics, int[] series) {
        return null;
    }

    public CharacterDto.CharacterInfoDto findInfoById(Long characterId) {
        return null;
    }
}
