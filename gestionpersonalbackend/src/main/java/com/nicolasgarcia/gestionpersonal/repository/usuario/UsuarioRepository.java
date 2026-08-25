package com.nicolasgarcia.gestionpersonal.repository.usuario;

import com.nicolasgarcia.gestionpersonal.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByUsername(String username);
}
