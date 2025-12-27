package com.example.demo;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testLogin() {

        AuthRequest request = new AuthRequest();
        request.setEmail("test@gmail.com");
        request.setPassword("password");

        AuthResponse response = userService.login(request);

        
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getToken());
        Assert.assertEquals(response.getEmail(), "test@gmail.com");
        Assert.assertEquals(response.getRole(), "USER");
    }
}
