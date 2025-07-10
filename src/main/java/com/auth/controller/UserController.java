package com.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/profile")
    public ResponseEntity<String> getProfile() {
        return ResponseEntity.ok("✅ Access granted for user profile");
        
        
    }
    
    @GetMapping("/play")
    public ResponseEntity<String> setprofile() {
        return ResponseEntity.ok("✅ Access granted for second one ");
    
    }
}

