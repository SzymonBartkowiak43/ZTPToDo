package com.example.ztptodo.user;


import com.example.ztptodo.user.dto.UserDto;

public class UserMapper {
    static UserDto map(User user) {
        return new UserDto(user.getUserName(), user.getPassword());
    }
}
