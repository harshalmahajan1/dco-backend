package com.example.dco.controller;

import com.example.dco.entity.UserAccount;
import com.example.dco.security.JwtUtil;
import com.example.dco.service.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserAccountService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserAccountService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        var created = userService.createUser(username, password);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        var userOpt = userService.findByUsername(username);
        if (userOpt.isEmpty()) return ResponseEntity.status(401).body("Invalid credentials");
        var user = userOpt.get();
        if (!userService.checkPassword(password, user.getPasswordHash())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        String token = jwtUtil.generateToken(username);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
