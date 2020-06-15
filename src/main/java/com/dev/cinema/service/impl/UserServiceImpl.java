package com.dev.cinema.service.impl;

import com.dev.cinema.dao.interfaces.UserDao;
import com.dev.cinema.model.User;
import com.dev.cinema.service.interfaces.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email).orElse(null);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }
}
