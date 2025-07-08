package com.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @GetMapping("/test")
    public ResponseEntity<String> securedEndpoint() {
        return ResponseEntity.ok("🔐 This is a secured endpoint. You should not access this yet.");
    }
}
