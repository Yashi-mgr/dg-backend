package com.dg.dental_guru.mapper;

import com.dg.dental_guru.dto.UserDTO;
import com.dg.dental_guru.model.User;

public class UserMapper {
    public static User mapToUser(UserDTO userDTO) {
        return new User(
                userDTO.getUserId(),
                userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getPhone()
        );
    }

    public static UserDTO mapToUserDTO(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone()
        );
    }
}
