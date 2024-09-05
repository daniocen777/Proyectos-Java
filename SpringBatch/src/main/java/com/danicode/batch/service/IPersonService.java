package com.danicode.batch.service;

import com.danicode.batch.entity.Person;

import java.util.List;

public interface IPersonService {
    void saveAll(List<Person> people);
}
