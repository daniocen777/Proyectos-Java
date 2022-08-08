package com.danicode.blog.security.service;

import com.danicode.blog.security.entity.Role;
import com.danicode.blog.security.enums.RoleList;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> getByRoleName(RoleList rolName);
}
