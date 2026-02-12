package com.dg.dental_guru.service.impls;

import com.dg.dental_guru.Repository.UserRepo;
import com.dg.dental_guru.config.JWTService;
import com.dg.dental_guru.dto.LoginDTO;
import com.dg.dental_guru.dto.UserDTO;
import com.dg.dental_guru.mapper.UserMapper;
import com.dg.dental_guru.model.UserRole;
import com.dg.dental_guru.model.Users;
import com.dg.dental_guru.response.ResponseMessage;
import com.dg.dental_guru.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpls implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public UserServiceImpls(UserRepo userRepo, PasswordEncoder passwordEncoder, JWTService jwtService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public String registerUser(UserDTO userDTO) {
       boolean emailExists = userRepo.existsByEmail(userDTO.getEmail());
       boolean phoneExists = userRepo.existsByPhone(userDTO.getPhone());
        if (emailExists) {
            throw new RuntimeException("Email already exists");
        }
        if (phoneExists) {
            throw new RuntimeException("Phone number already exists");
        }
        Users user = UserMapper.mapToUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.USER);
        userRepo.save(user);
        return "User " + user.getName() + " registered successfully";
    }

    @Override
    public ResponseMessage LoginUser(LoginDTO loginDTO) {
        Optional<Users> user = userRepo.findByEmail(loginDTO.getEmail());
        if (user.isPresent()) {
            Users userDB = user.get();
            Boolean passwordMatch = passwordEncoder.matches(loginDTO.getPassword(), userDB.getPassword());
            if (passwordMatch) {
                String token = jwtService.generateToken(userDB);
                return new ResponseMessage("Login successful.", true, token);
            }else {
                return new ResponseMessage("Incorrect password, Please try again.", false, null);
            }
        }else{
            return new ResponseMessage("Email does not exist.", false, null);
        }
    }

}
