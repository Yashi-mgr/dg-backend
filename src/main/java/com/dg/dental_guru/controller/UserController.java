package com.dg.dental_guru.controller;

import com.dg.dental_guru.dto.LoginDTO;
import com.dg.dental_guru.dto.UserDTO;
import com.dg.dental_guru.response.ResponseMessage;
import com.dg.dental_guru.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dg/users")
public class UserController {
    @Autowired
    private UserService userService;

    //Register users Rest API
    @PostMapping(path = "/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO) {
        try{
            return new ResponseEntity<>(userService.registerUser(userDTO), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // login users Rest API
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        ResponseMessage response = userService.LoginUser(loginDTO);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response); // HTTP 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response); // HTTP 401 Unauthorized
        }
    }
}
