package com.example.ztptodo.domain.user;


import com.example.ztptodo.domain.user.dto.UserDto;

public class UserMapper {
    static UserDto map(User user) {
        return new UserDto(user.getUserName(), user.getPassword());
    }
}
