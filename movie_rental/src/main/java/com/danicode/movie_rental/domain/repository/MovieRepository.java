package com.danicode.movie_rental.domain.repository;

import com.danicode.movie_rental.domain.dto.response.MovieResponse;

import java.util.List;

public interface MovieRepository {

    List<MovieResponse> getAllMovies();
}
