package com.danicode.blog.security.repositories;

import com.danicode.blog.security.entity.Role;
import com.danicode.blog.security.enums.RoleList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleList roleName);
}
