package com.nicolasgarcia.gestionpersonal.service.auth;

import com.nicolasgarcia.gestionpersonal.dto.Auth.AuthenticationRequestDTO;
import com.nicolasgarcia.gestionpersonal.dto.Auth.LoginRequestDTO;
import com.nicolasgarcia.gestionpersonal.dto.Auth.LoginResponseDTO;
import com.nicolasgarcia.gestionpersonal.dto.Auth.RefreshTokenResponseDTO;
import com.nicolasgarcia.gestionpersonal.dto.usuario.*;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO );
    LoginResponseDTO register(AuthenticationRequestDTO dto);
    RefreshTokenResponseDTO refreshToken(String authHeader);
    void logout(String authHeader);
}
