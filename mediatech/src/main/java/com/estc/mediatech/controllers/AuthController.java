package com.estc.mediatech.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("/me")
    public Map<String, String> getCurrentUser(Authentication authentication) {
        // Return { "role": "ROLE_ADMIN" } or { "role": "ROLE_USER" }
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        return Map.of("role", role);
    }
}