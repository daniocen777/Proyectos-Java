package com.danicode.app.dao;

import com.danicode.app.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleDao extends CrudRepository<Role, String> {
}
