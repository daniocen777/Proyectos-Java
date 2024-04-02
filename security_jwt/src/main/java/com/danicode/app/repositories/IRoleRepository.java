package com.danicode.app.repositories;

import com.danicode.app.models.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends CrudRepository<RoleEntity, Long> {
}
