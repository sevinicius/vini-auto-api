package com.okta.SAAM.Vini_autos.infraestructure.repository;

import com.okta.SAAM.Vini_autos.infraestructure.entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByEmail(String email);
}

