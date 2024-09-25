package com.danicode.marvel.persistence.integration.marvel.mapper;

import com.danicode.marvel.persistence.integration.marvel.dto.CharacterDto;
import com.danicode.marvel.persistence.integration.marvel.dto.ThumbnailDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.List;

public class CharacterMapper {

    public static List<CharacterDto> toDtoList(JsonNode rootNode) {
        ArrayNode resultsNode = getResultsNode(rootNode);

        List<CharacterDto> characters = new ArrayList<>();
        // Iterar en un Iterator en Lambda con forEachRemaining()
        resultsNode.elements().forEachRemaining(element -> characters.add(CharacterMapper.toDto(element)));

        return characters;
    }

    private static CharacterDto toDto(JsonNode characterNode) {
        if (characterNode == null) {
            throw new IllegalArgumentException("El nodo json no puede ser null");
        }

        return new CharacterDto(
                characterNode.get("id").asLong(),
                characterNode.get("name").asText(),
                characterNode.get("description").asText(),
                characterNode.get("modified").asText(),
                characterNode.get("resourceURI").asText()
        );
    }

    public static List<CharacterDto.CharacterInfoDto> toInfoDtoList(JsonNode response) {
        ArrayNode results = getResultsNode(response);

        List<CharacterDto.CharacterInfoDto> charactersInfoDto = new ArrayList<>();
        // Iterar en un Iterator en Lambda con forEachRemaining()
        results.elements().forEachRemaining(element -> charactersInfoDto.add(CharacterMapper.toInfoDto(element)));

        return charactersInfoDto;
    }

    private static CharacterDto.CharacterInfoDto toInfoDto(JsonNode characterInfoNode) {
        if (characterInfoNode == null) {
            throw new IllegalArgumentException("El nodo json no puede ser null");
        }

        JsonNode thumbnailNode = characterInfoNode.get("thumbnail");
        ThumbnailDto thumbnailDto = ThumbnailMapper.toDto(thumbnailNode);

        String image = thumbnailDto.getPath().concat(".").concat(thumbnailDto.getExtension());

        return new CharacterDto.CharacterInfoDto(
                image,
                characterInfoNode.get("description").asText()
        );
    }

    private static ArrayNode getResultsNode(JsonNode rootNode) {
        if (rootNode == null) {
            throw new IllegalArgumentException("El nodo json no puede ser null");
        }

        JsonNode dataNode = rootNode.get("data");

        return (ArrayNode) dataNode.get("results");
    }
}
