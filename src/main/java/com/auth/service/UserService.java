package com.auth.service;

import com.auth.dto.UserDto;
import com.auth.entity.User;
import com.auth.repository.UserRepository;
import com.auth.security.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(UserDto dto) {

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists with this email");
        }

        String hashedPassword = passwordEncoder.encode(dto.getPassword());

        User user = new User(dto.getUsername(), dto.getEmail(), hashedPassword);

        userRepository.save(user);
    }
    
//  updated authenticateuser 
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public String authenticateUser(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtTokenUtil.generateToken(user.getEmail());
    }

}

