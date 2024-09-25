package com.danicode.marvel.persistence.integration.marvel.repository;

import com.danicode.marvel.dto.CustomPageable;
import com.danicode.marvel.persistence.integration.marvel.MarvelAPIConfig;
import com.danicode.marvel.persistence.integration.marvel.dto.ComicDto;
import com.danicode.marvel.persistence.integration.marvel.mapper.ComicMapper;
import com.danicode.marvel.service.HttpClientService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

import java.util.List;
import java.util.Map;

@Repository
public class ComicRepository {

    // Autorizacion a la api
    private final MarvelAPIConfig marvelAPIConfig;

    private final HttpClientService httpClientService;

    @Value("${integration.marvel.base-path}")
    private String basePath;

    private String comicPath;

    public ComicRepository(MarvelAPIConfig marvelAPIConfig, HttpClientService httpClientService) {
        this.marvelAPIConfig = marvelAPIConfig;
        this.httpClientService = httpClientService;
    }

    // Luego de crear el bean
    @PostConstruct
    private void setPath() {
        comicPath = basePath.concat("/").concat("comics");
    }

    public List<ComicDto> findAll(CustomPageable pageable, Long characterId) {
        // Query paramas
        Map<String, String> marvelQueryParams = getQueryParamsForFindAll(pageable, characterId);
        JsonNode response = httpClientService.doGet(comicPath, marvelQueryParams, JsonNode.class);

        return ComicMapper.toDtoList(response);
    }

    public ComicDto findById(Long comicId) {
        // Query paramas
        Map<String, String> marvelQueryParams = marvelAPIConfig.getAuthenticationQueryParams();
        String finalUrl = comicPath.concat("/").concat(Long.toString(comicId));

        JsonNode response = httpClientService.doGet(finalUrl, marvelQueryParams, JsonNode.class);

        return ComicMapper.toDtoList(response).get(0);
    }

    private Map<String, String> getQueryParamsForFindAll(CustomPageable pageable, Long characterId) {
        Map<String, String> marvelQueryParams = marvelAPIConfig.getAuthenticationQueryParams();
        // queries para la consulta
        marvelQueryParams.put("offset", Long.toString(pageable.getOffset()));
        marvelQueryParams.put("limit", Long.toString(pageable.getLimit()));

        if (characterId != null && characterId > 0) {
            marvelQueryParams.put("characters", Long.toString(characterId));
        }

        return marvelQueryParams;
    }
}
