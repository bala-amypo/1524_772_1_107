package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final JwtUtil jwtUtil;

    public UserServiceImpl(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public User register(User user) {
        return user;
    }

    @Override
    public AuthResponse login(AuthRequest request) {

        Long userId = 1L;
        String email = request.getEmail();
        String role = "AGENT";
        
        String token = jwtUtil.generateToken(email, role);

        return new AuthResponse(token, userId, email, role);
    }
}

