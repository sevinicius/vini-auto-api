package com.okta.SAAM.Vini_autos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.okta.SAAM.Vini_autos.busines.AuthService;
import com.okta.SAAM.Vini_autos.busines.dto.LoginRequest;
import com.okta.SAAM.Vini_autos.busines.dto.LoginResponse;
import com.okta.SAAM.Vini_autos.infraestructure.entitys.Usuario;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            System.out.println("Login attempt for username: " + loginRequest.getUsername());
            LoginResponse response = authService.login(loginRequest);
            System.out.println("Login successful for user: " + response.getUsername());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            System.out.println("Login failed: " + e.getMessage());
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        try {
            System.out.println("Register attempt for username: " + usuario.getUsername());
            Usuario savedUsuario = authService.register(usuario);
            System.out.println("Register successful for user: " + savedUsuario.getUsername());
            return ResponseEntity.ok(savedUsuario);
        } catch (RuntimeException e) {
            System.out.println("Register failed: " + e.getMessage());
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}

