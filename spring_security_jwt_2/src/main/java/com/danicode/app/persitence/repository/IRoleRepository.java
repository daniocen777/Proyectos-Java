package com.danicode.app.persitence.repository;

import com.danicode.app.persitence.entity.RoleEntity;
import com.danicode.app.persitence.enums.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleRepository extends CrudRepository<RoleEntity, Long> {

    List<RoleEntity> findRoleEntityByRoleEnumIn(List<RoleEnum> roleNames);
}
