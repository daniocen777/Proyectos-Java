package com.danicode.app.controller;

import com.danicode.app.entity.User;
import com.danicode.app.service.UserService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping({"/createUser"})
    public User create(@RequestBody User user) {
        return service.create(user);
    }
}
