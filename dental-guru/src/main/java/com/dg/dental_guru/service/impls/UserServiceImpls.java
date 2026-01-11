package com.dg.dental_guru.service.impls;

import com.dg.dental_guru.Repository.UserRepo;
import com.dg.dental_guru.dto.LoginDTO;
import com.dg.dental_guru.dto.UserDTO;
import com.dg.dental_guru.mapper.UserMapper;
import com.dg.dental_guru.model.User;
import com.dg.dental_guru.response.ResponseMessage;
import com.dg.dental_guru.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpls implements UserService {

    private final UserRepo userRepo;
//    private final PasswordEncoder passwordEncoder;

    public UserServiceImpls(UserRepo userRepo) {
        this.userRepo = userRepo;
//        this.passwordEncoder = passwordEncoder;
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
        User user = UserMapper.mapToUser(userDTO);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "User " + user.getName() + " registered successfully";
    }

    @Override
    public ResponseMessage LoginUser(LoginDTO loginDTO) {
        Optional<User> user = userRepo.findByEmail(loginDTO.getEmail());
        if (user.isPresent()) {
            User userDB = user.get();
            Boolean passwordMatch = userDB.getPassword().equals(loginDTO.getPassword());
//            Boolean passwordMatch = passwordEncoder.matches(loginDTO.getPassword(), userDB.getPassword());
            if (passwordMatch) {
                return new ResponseMessage("Login successful.", true);
            }else {
                return new ResponseMessage("Incorrect password, Please try again.", false);
            }
        }else{
            return new ResponseMessage("Email does not exist.", false);
        }
    }

}
