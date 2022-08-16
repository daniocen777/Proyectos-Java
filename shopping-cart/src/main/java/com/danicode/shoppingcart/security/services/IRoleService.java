package com.danicode.shoppingcart.security.services;

import com.danicode.shoppingcart.security.entities.Role;
import com.danicode.shoppingcart.security.enums.RoleList;

import java.util.Optional;

public interface IRoleService {

    Optional<Role> getByRoleName(RoleList roleName);
}
