package com.nicolasgarcia.gestionpersonal.mapper.auth;
import com.nicolasgarcia.gestionpersonal.dto.Auth.AuthenticationRequestDTO;
import com.nicolasgarcia.gestionpersonal.dto.Auth.LoginRequestDTO;
import com.nicolasgarcia.gestionpersonal.dto.Auth.LoginResponseDTO;
import com.nicolasgarcia.gestionpersonal.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {
    public LoginResponseDTO toDTO(Usuario usuario,
                                  String token,
                                  String tokenRefresh) {
        if (usuario == null) {
            return null;
        }
        return LoginResponseDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .rol(usuario.getRol())
                .token(token)
                .tokenRefresh(tokenRefresh)
                .build();
    }

    public Usuario toEntity(AuthenticationRequestDTO dto){
        if (dto == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());
        usuario.setEmail(dto.getEmail());
        return usuario;
    }
}
