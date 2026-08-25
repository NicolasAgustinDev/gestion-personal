package com.nicolasgarcia.gestionpersonal.service.imp.auth;
import com.nicolasgarcia.gestionpersonal.dto.Auth.AuthenticationRequestDTO;
import com.nicolasgarcia.gestionpersonal.dto.Auth.LoginRequestDTO;
import com.nicolasgarcia.gestionpersonal.dto.Auth.LoginResponseDTO;
import com.nicolasgarcia.gestionpersonal.dto.Auth.RefreshTokenResponseDTO;
import com.nicolasgarcia.gestionpersonal.dto.usuario.*;
import com.nicolasgarcia.gestionpersonal.entity.Usuario;
import com.nicolasgarcia.gestionpersonal.enums.usuario.Rol;
import com.nicolasgarcia.gestionpersonal.mapper.auth.AuthMapper;
import com.nicolasgarcia.gestionpersonal.mapper.usuario.UsuarioMapper;
import com.nicolasgarcia.gestionpersonal.repository.usuario.UsuarioRepository;
import com.nicolasgarcia.gestionpersonal.security.CustomUserDetails;
import com.nicolasgarcia.gestionpersonal.security.CustomUserDetailsService;
import com.nicolasgarcia.gestionpersonal.security.JwtService;
import com.nicolasgarcia.gestionpersonal.service.auth.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService {
    private final UsuarioRepository usuarioRepository;
    private final AuthMapper authMapper;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImp (UsuarioRepository usuarioRepository,
                           AuthMapper authMapper,
                           UsuarioMapper usuarioMapper,
                           PasswordEncoder passwordEncoder,
                           CustomUserDetailsService customUserDetailsService,
                           JwtService jwtService,
                           AuthenticationManager authenticationManager){
        this.authMapper = authMapper;
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder= passwordEncoder;
        this.customUserDetailsService=customUserDetailsService;
        this.jwtService= jwtService;
        this.authenticationManager= authenticationManager;
    }
    // ============================
    // LOG IN
    // ============================
    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){
        Authentication authentication =
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getUsername(),
                            loginRequestDTO.getPassword()
                    )
            );
        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        Usuario usuario = userDetails.getUsuario();
        String jwt = jwtService.generateToken(userDetails);
        String tokenRefresh = jwtService.generateRefreshToken(userDetails);
        return authMapper.toDTO(usuario,jwt,tokenRefresh);
    }
    // ============================
    // REGISTER
    // ============================
    @Override
    public LoginResponseDTO register(AuthenticationRequestDTO dto){
        Usuario usuario = authMapper.toEntity(dto);
        usuario.setPassword(
                passwordEncoder.encode(usuario.getPassword())
        );
        usuario.setRol(Rol.EMPLEADO);
        Usuario saved = usuarioRepository.save(usuario);
        CustomUserDetails userDetails = new CustomUserDetails(saved);
        String accessToken = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);
        return authMapper.toDTO(saved,accessToken,refreshToken);
    }
    // ============================
    // REFRESH TOKEN
    // ============================
    @Override
    public RefreshTokenResponseDTO refreshToken(String authHeader){
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid Bearer token");
        }
        String refreshToken = authHeader.substring(7);
        String username = jwtService.extractUsername(refreshToken);
        UserDetails userDetails =
                customUserDetailsService.loadUserByUsername(username);
        jwtService.isTokenValid(refreshToken, userDetails);
        String accessToken =
                jwtService.generateToken(userDetails);
        return new RefreshTokenResponseDTO(
                accessToken,
                refreshToken
        );
    }
    // ============================
    // LOG OUT
    // ============================
    @Override
    public void logout(String authHeader){
        return;
    }



}
