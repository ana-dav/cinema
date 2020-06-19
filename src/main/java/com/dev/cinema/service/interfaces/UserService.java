package com.dev.cinema.service.interfaces;

import com.dev.cinema.model.User;

public interface UserService {
    User add(User user);

    User findByEmail(String email);

    User getUserById(Long id);
}
