package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;

public interface UserService {

    User register(User user);

    AuthResponse login(AuthRequest request);
}
