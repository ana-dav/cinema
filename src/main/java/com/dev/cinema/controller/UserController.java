package com.dev.cinema.controller;

import com.dev.cinema.mappers.UserMapper;
import com.dev.cinema.model.dto.UserResponseDto;
import com.dev.cinema.service.interfaces.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userDtoMapper) {
        this.userService = userService;
        this.userMapper = userDtoMapper;
    }

    @GetMapping("/by-email")
    public UserResponseDto getByEmail(@RequestParam String email) {
        return userMapper.userToDto(userService.findByEmail(email));
    }
}
