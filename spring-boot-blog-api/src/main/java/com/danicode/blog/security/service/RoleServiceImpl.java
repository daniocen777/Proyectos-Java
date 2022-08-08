package com.danicode.blog.security.service;

import com.danicode.blog.security.entity.Role;
import com.danicode.blog.security.enums.RoleList;
import com.danicode.blog.security.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Optional<Role> getByRoleName(RoleList rolName) {
        return roleRepository.findByRoleName(rolName);
    }
}
