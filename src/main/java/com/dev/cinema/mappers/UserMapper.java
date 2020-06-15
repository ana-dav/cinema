package com.dev.cinema.mappers;

import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.UserRequestDto;
import com.dev.cinema.model.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User dtoToUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setPassword(userRequestDto.getPassword());
        user.setEmail(userRequestDto.getEmail());

        return user;
    }

    public UserResponseDto userToDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPassword(user.getPassword());
        userResponseDto.setUserId(user.getId());

        return userResponseDto;
    }
}
