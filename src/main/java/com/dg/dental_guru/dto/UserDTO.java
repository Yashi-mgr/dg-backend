package com.dg.dental_guru.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer userId;

    @NotEmpty(message = "Name cannot be empty.")
    private String name;

    @NotEmpty(message = "Email cannot be empty.")
    @Email(message = "Enter a valid email.")
    private String email;

    @NotEmpty(message = "Password cannot be empty.")
    @Size(max = 12, min = 6, message = "Password must be of 6-12 characters")
    private String password;

    @NotEmpty(message = "Phone number cannot be empty.")
    private String phone;
}
