package com.dg.dental_guru.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> checkUserDetails(HttpServletRequest request);
}
