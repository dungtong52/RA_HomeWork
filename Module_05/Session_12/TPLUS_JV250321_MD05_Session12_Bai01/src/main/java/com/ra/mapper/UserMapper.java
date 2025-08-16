package com.ra.mapper;

import com.ra.dto.UserRegisterDto;
import com.ra.model.User;

public class UserMapper {
    public static User toModel(UserRegisterDto dto, String avatarURL) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setFullName(dto.getFullName());
        user.setAvatar(avatarURL);
        return user;
    }
}
