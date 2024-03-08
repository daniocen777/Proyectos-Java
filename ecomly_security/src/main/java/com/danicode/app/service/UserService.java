package com.danicode.app.service;

import com.danicode.app.dao.IRoleDao;
import com.danicode.app.dao.IUserDao;
import com.danicode.app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private IUserDao userDao;

    public User create(User user) {
        return userDao.save(user);
    }
}
