package com.dg.dental_guru.service;

import com.dg.dental_guru.dto.LoginDTO;
import com.dg.dental_guru.dto.UserDTO;
import com.dg.dental_guru.response.ResponseMessage;

public interface UserService {
    String registerUser( UserDTO userDTO);
    ResponseMessage LoginUser(LoginDTO loginDTO);
}
