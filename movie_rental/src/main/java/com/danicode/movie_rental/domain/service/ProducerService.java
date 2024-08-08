package com.danicode.movie_rental.domain.service;

import com.danicode.movie_rental.core.exception.ProducerFoundException;
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

    public Producer getProducerById(Long producerId) {
        return repository.getProducerById(producerId);
    }

    public Producer save(Producer producer) {
        Producer producerByName = repository.getProducerByName(producer.getCompanyName());
        if (producerByName != null) {
            throw new ProducerFoundException();
        }

        return repository.saveProducer(producer);
    }

    public Producer getProducerByCompanyName(String companyName) {
        return repository.getProducerByName(companyName);
    }
}
