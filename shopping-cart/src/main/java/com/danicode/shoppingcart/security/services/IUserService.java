package com.danicode.shoppingcart.security.services;

import com.danicode.shoppingcart.security.entities.User;

import java.util.Optional;

public interface IUserService {

    Optional<User> getByUsername(String username);

    boolean existByUsername(String username);

    void save(User user);
}
