package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.security.JwtResponse;
import com.example.demo.security.JwtUtil;
import com.example.demo.security.LoginRequest;
import com.example.demo.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    /* ---------- REGISTER ---------- */
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    /* ---------- LOGIN ---------- */
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(
            @RequestBody LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );

        User user = userService.findByEmail(request.getEmail());

        String token = jwtUtil.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
