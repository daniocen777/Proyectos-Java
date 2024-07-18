package com.danicode.movie_rental.domain.repository;

import com.danicode.movie_rental.domain.dto.pojo.Producer;

import java.util.List;

public interface ProducerRepository {
    List<Producer> getAllProducers();
}
