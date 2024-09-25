package com.danicode.marvel.persistence.integration.marvel.repository;

import com.danicode.marvel.dto.CustomPageable;
import com.danicode.marvel.persistence.integration.marvel.MarvelAPIConfig;
import com.danicode.marvel.persistence.integration.marvel.dto.CharacterDto;
import com.danicode.marvel.persistence.integration.marvel.mapper.CharacterMapper;
import com.danicode.marvel.service.HttpClientService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class CharacterRepository {

    // Autorizacion a la api
    private final MarvelAPIConfig marvelAPIConfig;

    private final HttpClientService httpClientService;

    @Value("${integration.marvel.base-path}")
    private String basePath;

    private String characterPath;

    // Luego de crear el bean
    @PostConstruct
    private void setPath() {
        characterPath = basePath.concat("/").concat("characters");
    }

    public CharacterRepository(MarvelAPIConfig marvelAPIConfig, HttpClientService httpClientService) {
        this.marvelAPIConfig = marvelAPIConfig;
        this.httpClientService = httpClientService;
    }

    public List<CharacterDto> findAll(CustomPageable pageable, String name, int[] comics, int[] series) {
        // Query paramas
        Map<String, String> marvelQueryParams = getQueryParamsForFindAll(pageable, name, comics, series);
        // Peticion que devuelve un json
        JsonNode response = httpClientService.doGet(characterPath, marvelQueryParams, JsonNode.class);

        return CharacterMapper.toDtoList(response);
    }

    public CharacterDto.CharacterInfoDto findInfoById(Long characterId) {
        // * characterId sera un PathVariable, no un queryParams
        // queries de autenticacion
        Map<String, String> marvelQueryParams = marvelAPIConfig.getAuthenticationQueryParams();
        String finalUrl = characterPath.concat("/").concat(Long.toString(characterId));
        // Peticion que devuelve un json
        JsonNode response = httpClientService.doGet(finalUrl, marvelQueryParams, JsonNode.class);

        return CharacterMapper.toInfoDtoList(response).get(0);

    }

    private Map<String, String> getQueryParamsForFindAll(
            CustomPageable pageable,
            String name,
            int[] comics,
            int[] series) {
        // queries de autenticacion
        Map<String, String> marvelQueryParams = marvelAPIConfig.getAuthenticationQueryParams();
        // queries para la consulta
        marvelQueryParams.put("offset", Long.toString(pageable.getOffset()));
        marvelQueryParams.put("limit", Long.toString(pageable.getLimit()));

        if (StringUtils.hasText(name)) {
            marvelQueryParams.put("name", name);
        }

        if (comics != null) {
            String comicsAsString = this.joinIntArray(comics);
            marvelQueryParams.put("comics", comicsAsString);
        }

        if (series != null) {
            String seriesAsString = this.joinIntArray(series);
            marvelQueryParams.put("series", seriesAsString);
        }

        return marvelQueryParams;
    }

    private String joinIntArray(int[] intArray) {
        List<String> stringArray = IntStream.of(intArray).boxed()
                .map(Object::toString)
                .collect(Collectors.toList());

        return String.join(",", stringArray);
    }
}
