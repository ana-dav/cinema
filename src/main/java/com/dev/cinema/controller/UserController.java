package com.dev.cinema.controller;

import com.dev.cinema.mappers.UserMapper;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.UserResponseDto;
import com.dev.cinema.service.interfaces.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    //
    private final UserService userService;
    private final UserMapper userDtoMapper;

    public UserController(UserService userService, UserMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping("/by-email")
    public UserResponseDto getByEmail(Authentication authentication) {
        String emailLogin = authentication.getName();
        User user = userService.findByEmail(emailLogin);
        return userDtoMapper.userToDto(user);
    }
}
