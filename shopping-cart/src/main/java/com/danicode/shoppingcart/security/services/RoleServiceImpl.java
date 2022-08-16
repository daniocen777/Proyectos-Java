package com.danicode.shoppingcart.security.services;

import com.danicode.shoppingcart.security.entities.Role;
import com.danicode.shoppingcart.security.enums.RoleList;
import com.danicode.shoppingcart.security.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Optional<Role> getByRoleName(RoleList roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
