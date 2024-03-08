package com.danicode.app.controller;

import com.danicode.app.entity.Role;
import com.danicode.app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private RoleService service;

    @PostMapping({"/createRole"})
    public Role create(@RequestBody Role role) {
        return service.create(role);
    }
}
