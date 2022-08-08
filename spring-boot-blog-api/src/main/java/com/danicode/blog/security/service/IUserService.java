package com.danicode.blog.security.service;

import com.danicode.blog.security.entity.User;

import java.util.Optional;

public interface IUserService {

    Optional<User> getByUsernane(String username);

    boolean existByUserName(String userName);

    void save(User user);
}
