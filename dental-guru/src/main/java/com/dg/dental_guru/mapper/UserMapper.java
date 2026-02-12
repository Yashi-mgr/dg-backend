package com.dg.dental_guru.mapper;

import com.dg.dental_guru.dto.UserDTO;
import com.dg.dental_guru.model.Users;

public class UserMapper {
    public static Users mapToUser(UserDTO userDTO) {
        return new Users(
                userDTO.getUserId(),
                userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getPhone(),
                userDTO.getRole()
        );
    }

    public static UserDTO mapToUserDTO(Users user) {
        return new UserDTO(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone(),
                user.getRole()
        );
    }
}
