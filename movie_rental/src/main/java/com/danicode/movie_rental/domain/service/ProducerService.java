package com.danicode.movie_rental.domain.service;

import com.danicode.movie_rental.domain.dto.pojo.Producer;
import com.danicode.movie_rental.domain.repository.ProducerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducerService {

    private final ProducerRepository repository;

    public ProducerService(ProducerRepository repository) {
        this.repository = repository;
    }

    public List<Producer> getAll() {
        return this.repository.getAllProducers();
    }
}
