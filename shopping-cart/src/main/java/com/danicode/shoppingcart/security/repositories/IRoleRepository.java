package com.danicode.shoppingcart.security.repositories;

import com.danicode.shoppingcart.security.entities.Role;
import com.danicode.shoppingcart.security.enums.RoleList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(RoleList roleName);
}
