package com.danicode.marvel.persistence.integration.marvel.mapper;

import com.danicode.marvel.persistence.integration.marvel.dto.ThumbnailDto;
import com.fasterxml.jackson.databind.JsonNode;

public class ThumbnailMapper {

    public static ThumbnailDto toDto(JsonNode thumbnailDto) {
        if (thumbnailDto == null) {
            throw new IllegalArgumentException("El nodo json no puede ser null");
        }

        return new ThumbnailDto(
                thumbnailDto.get("path").asText(),
                thumbnailDto.get("extension").asText()
        );

    }
}
