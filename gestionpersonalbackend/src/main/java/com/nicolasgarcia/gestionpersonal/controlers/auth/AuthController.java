package com.nicolasgarcia.gestionpersonal.controlers.auth;
import com.nicolasgarcia.gestionpersonal.dto.Auth.*;
import com.nicolasgarcia.gestionpersonal.service.imp.auth.AuthServiceImp;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AuthController {
    private final AuthServiceImp authService;
    public AuthController(AuthServiceImp authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponseDTO> register(
            @Valid @RequestBody AuthenticationRequestDTO dto){
        LoginResponseDTO response = authService.register(dto);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO response = authService.login(loginRequestDTO);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/refresh")
    public RefreshTokenResponseDTO refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader){
        return authService.refreshToken(authHeader);
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader){
        this.authService.logout(authHeader);
        return ResponseEntity.noContent().build();
    }
}
