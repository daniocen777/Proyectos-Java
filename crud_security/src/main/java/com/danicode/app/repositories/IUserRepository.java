package com.danicode.app.repositories;

import com.danicode.app.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User, Long> {
}
