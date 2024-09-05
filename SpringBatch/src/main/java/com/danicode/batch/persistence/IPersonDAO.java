package com.danicode.batch.persistence;

import com.danicode.batch.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface IPersonDAO extends CrudRepository<Person, Long> {
}
