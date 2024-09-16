package com.danicode.marvel.service;

import com.danicode.marvel.dto.CustomPageable;
import com.danicode.marvel.persistence.integration.marvel.dto.ComicDto;

import java.util.List;

public interface IComicService {
    List<ComicDto> findAll(CustomPageable pageable, Long characterId);

    ComicDto findById(Long comicId);
}
