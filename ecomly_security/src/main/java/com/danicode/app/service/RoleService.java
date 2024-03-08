package com.danicode.app.service;

import com.danicode.app.dao.IRoleDao;
import com.danicode.app.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private IRoleDao dao;

    public Role create(Role role) {
        return dao.save(role);
    }
}
