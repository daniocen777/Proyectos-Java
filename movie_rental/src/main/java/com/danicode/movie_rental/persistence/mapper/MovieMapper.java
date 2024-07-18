package com.danicode.movie_rental.persistence.mapper;

import com.danicode.movie_rental.domain.dto.response.MovieResponse;
import com.danicode.movie_rental.persistence.entity.MovieEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieResponse toMovie(MovieEntity movieEntity);

    @InheritInverseConfiguration
    MovieEntity toMovieEntity(MovieResponse movieResponse);

    List<MovieResponse> toMoviesList(List<MovieEntity> movieEntities);
}
