package com.dev.cinema.security;

import com.dev.cinema.model.User;
import javax.security.sasl.AuthenticationException;

public interface AuthenticationService {
    User login(String login, String password) throws AuthenticationException;

    User register(String email, String password);
}
