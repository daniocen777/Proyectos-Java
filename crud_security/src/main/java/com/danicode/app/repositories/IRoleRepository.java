package com.danicode.app.repositories;

import com.danicode.app.entities.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IRoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
