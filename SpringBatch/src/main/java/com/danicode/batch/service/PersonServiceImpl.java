package com.danicode.batch.service;

import com.danicode.batch.entity.Person;
import com.danicode.batch.persistence.IPersonDAO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PersonServiceImpl implements IPersonService {

    private final IPersonDAO personDAO;

    public PersonServiceImpl(IPersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    @Transactional
    public void saveAll(List<Person> people) {
        personDAO.saveAll(people);
    }
}
