package com.anncode.amazonviewer.dao;

import com.anncode.amazonviewer.model.Movie;

import java.util.ArrayList;

/**
 * <h1>Movie</h1>
 * <p>
 * Interface que contiene métodos de tipo default (métodos
 * que aceptan implementación - desde java 8) que se usarán
 * para el CRUD de la tabla movie
 * </p>
 */
public interface MovieDao {
    default Movie setMovieViewer(Movie movie) {
        return movie;
    }

    default ArrayList<Movie> read() {
        ArrayList<Movie> movies = new ArrayList<>();
        return movies;
    }

    private boolean getMovieViewed() {
        return false;
    }
}
