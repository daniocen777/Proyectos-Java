package com.danicode.marvel.service.impl;

import com.danicode.marvel.dto.CustomPageable;
import com.danicode.marvel.persistence.integration.marvel.dto.ComicDto;
import com.danicode.marvel.persistence.integration.marvel.repository.ComicRepository;
import com.danicode.marvel.service.IComicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComicServiceImpl implements IComicService {

    private final ComicRepository repository;

    public ComicServiceImpl(ComicRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ComicDto> findAll(CustomPageable pageable, Long characterId) {
        return repository.findAll(pageable, characterId);
    }

    @Override
    public ComicDto findById(Long comicId) {
        return repository.findById(comicId);
    }
}
