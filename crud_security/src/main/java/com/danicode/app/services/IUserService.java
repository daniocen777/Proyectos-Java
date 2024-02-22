package com.danicode.app.services;

import com.danicode.app.entities.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();

    User save(User user);
}
