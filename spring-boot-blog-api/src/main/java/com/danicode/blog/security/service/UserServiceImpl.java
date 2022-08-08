package com.danicode.blog.security.service;

import com.danicode.blog.security.entity.User;
import com.danicode.blog.security.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Optional<User> getByUsernane(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean existByUserName(String userName) {
        return userRepository.existsByUsername(userName);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
