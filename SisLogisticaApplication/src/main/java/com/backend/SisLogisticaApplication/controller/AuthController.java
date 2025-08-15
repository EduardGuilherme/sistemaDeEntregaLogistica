package com.backend.SisLogisticaApplication.controller;

import com.backend.SisLogisticaApplication.dtos.LoginRequest;
import com.backend.SisLogisticaApplication.dtos.RegisterRequest;
import com.backend.SisLogisticaApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest request) {
        userService.register(request.username(), request.password(), request.role());
        return ResponseEntity.ok(Map.of("message", "Usuário registrado com sucesso!"));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        String token = userService.login(request.username(), request.password());
        return ResponseEntity.ok(Map.of("token", token));
    }
}
