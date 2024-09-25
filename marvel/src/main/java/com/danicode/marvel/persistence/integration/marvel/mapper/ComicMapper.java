package com.danicode.marvel.persistence.integration.marvel.mapper;

import com.danicode.marvel.persistence.integration.marvel.dto.ComicDto;
import com.danicode.marvel.persistence.integration.marvel.dto.ThumbnailDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.List;

public class ComicMapper {

    public static List<ComicDto> toDtoList(JsonNode rootNode) {
        ArrayNode resultsNode = getResultsNode(rootNode);
        List<ComicDto> comics = new ArrayList<>();

        resultsNode.elements().forEachRemaining(element -> comics.add(ComicMapper.toDto(element)));

        return comics;
    }

    private static ComicDto toDto(JsonNode comicNode) {
        if (comicNode == null) {
            throw new IllegalArgumentException("El nodo json no puede ser null");
        }

        ThumbnailDto thumbnail = ThumbnailMapper.toDto(comicNode.get("thumbnail"));

        return new ComicDto(
                comicNode.get("id").asLong(),
                comicNode.get("title").asText(),
                comicNode.get("description").asText(),
                comicNode.get("modified").asText(),
                comicNode.get("resourceURI").asText(),
                thumbnail
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
