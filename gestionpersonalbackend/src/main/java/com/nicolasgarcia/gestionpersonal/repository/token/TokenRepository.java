package com.nicolasgarcia.gestionpersonal.repository.token;

import com.nicolasgarcia.gestionpersonal.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<RefreshToken,Long> {
    Optional<RefreshToken> findByToken(String token);
}
