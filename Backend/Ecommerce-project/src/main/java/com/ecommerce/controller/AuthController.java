package com.ecommerce.controller;

import com.ecommerce.dto.LoginRequest;
import com.ecommerce.entity.User;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    
    @PostMapping("/login")
    public User login(@RequestBody LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElse(null);

        if(user != null && user.getPassword().equals(request.getPassword())){
            return user;
        }

        throw new RuntimeException("Invalid email or password");
    }    
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logout successful";
    }
    
    @GetMapping("/me")
    public Long getLoggedInUser(HttpSession session) {
        return (Long) session.getAttribute("userId");
    }
}