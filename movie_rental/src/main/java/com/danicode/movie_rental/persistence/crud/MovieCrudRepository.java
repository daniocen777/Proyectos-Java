package com.danicode.movie_rental.persistence.crud;

import com.danicode.movie_rental.persistence.entity.MovieEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieCrudRepository extends CrudRepository<MovieEntity, Long> {
    List<MovieEntity> findByProducerId(Long producerId);
}
