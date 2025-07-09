package com.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
	@EnableWebSecurity
	public class SecurityConfig {

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable()) // 🔒 Disable CSRF for API
                    .headers(headers -> headers.frameOptions(frame -> frame.disable())) 
                    .authorizeHttpRequests(requests -> requests
                            .requestMatchers("/api/auth/**").permitAll() // ✅ Public endpoints
                            .requestMatchers("/h2-console/**").permitAll() // Allow console access
                            .anyRequest().authenticated()); // 🔐 All other endpoints require authentication
                            
            http.formLogin(login -> login.disable());
            http.httpBasic(basic -> basic.disable());
            
	        return http.build();
	    }
	}