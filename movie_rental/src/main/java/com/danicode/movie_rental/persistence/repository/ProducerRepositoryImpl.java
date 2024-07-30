package com.danicode.movie_rental.persistence.repository;

import com.danicode.movie_rental.domain.dto.pojo.Producer;
import com.danicode.movie_rental.domain.repository.ProducerRepository;
import com.danicode.movie_rental.persistence.crud.ProducerCrudRepository;
import com.danicode.movie_rental.persistence.entity.ProducerEntity;
import com.danicode.movie_rental.persistence.mapper.ProducerMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProducerRepositoryImpl implements ProducerRepository {

    private final ProducerCrudRepository repository;

    private final ProducerMapper mapper;

    public ProducerRepositoryImpl(ProducerCrudRepository repository, ProducerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Producer> getAllProducers() {
        List<ProducerEntity> producerEntities = (List<ProducerEntity>) repository.findAll();
        return mapper.toProducersList(producerEntities);
    }

    @Override
    public Producer save(Producer producer) {
        ProducerEntity producerEntity = mapper.toProducerEntity(producer);
        return mapper.toProducer(repository.save(producerEntity));
    }
}
