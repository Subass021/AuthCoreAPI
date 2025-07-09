package com.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.auth.dto.UserDto;
import com.auth.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AutController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto dto) {
        userService.registerUser(dto);
        return ResponseEntity.ok("User registered successfully");
    }
}