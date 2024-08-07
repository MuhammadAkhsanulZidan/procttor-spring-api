package com.procttor.api.dto.mapper;

import com.procttor.api.dto.UserDto;
import com.procttor.api.model.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto(user.getUuid(), user.getName(), user.getEmail());
        return userDto;
    }
}