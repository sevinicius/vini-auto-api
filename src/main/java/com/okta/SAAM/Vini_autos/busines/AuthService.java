package com.okta.SAAM.Vini_autos.busines;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.okta.SAAM.Vini_autos.busines.dto.LoginRequest;
import com.okta.SAAM.Vini_autos.busines.dto.LoginResponse;
import com.okta.SAAM.Vini_autos.infraestructure.entitys.Usuario;
import com.okta.SAAM.Vini_autos.infraestructure.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest loginRequest) {
        Optional<Usuario> usuario = usuarioRepository.findByUsername(loginRequest.getUsername());
        
        if (usuario.isEmpty() || !passwordEncoder.matches(loginRequest.getPassword(), usuario.get().getPassword())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        String token = jwtService.generateToken(usuario.get().getUsername(), usuario.get().getRole());
        
        return new LoginResponse(token, usuario.get().getUsername(), usuario.get().getRole());
    }

    public Usuario register(Usuario usuario) {
        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            throw new RuntimeException("Username já existe");
        }
        
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("Email já existe");
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }
}

