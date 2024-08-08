package com.danicode.movie_rental.persistence.crud;

import com.danicode.movie_rental.persistence.entity.ProducerEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProducerCrudRepository extends CrudRepository<ProducerEntity, Long> {
    ProducerEntity findByCompanyName(String companyName);
}
