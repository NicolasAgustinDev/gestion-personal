package com.nicolasgarcia.gestionpersonal.mapper.usuario;
import com.nicolasgarcia.gestionpersonal.dto.usuario.UsuarioRequestDTO;
import com.nicolasgarcia.gestionpersonal.dto.usuario.UsuarioResponseDTO;
import com.nicolasgarcia.gestionpersonal.dto.usuario.UsuarioUpdateDTO;
import com.nicolasgarcia.gestionpersonal.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public UsuarioResponseDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .username(usuario.getUsername())
                .email(usuario.getEmail())
                .rol(usuario.getRol())
                .estado(usuario.getEstado())
                .build();
    }
    public Usuario toEntity(UsuarioRequestDTO dto ){
        if (dto == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setUsername(dto.getUsername());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword());
        usuario.setRol(dto.getRol());
        return usuario;
    }
    public Usuario updateEntity(UsuarioUpdateDTO updateDTO,
                                  Usuario usuario ){
        if (updateDTO == null) {
            return null;
        }
        usuario.setNombre(updateDTO.getNombre());
        usuario.setApellido(updateDTO.getApellido());
        usuario.setUsername(updateDTO.getUsername());
        usuario.setEmail(updateDTO.getEmail());
        return usuario;
    }
}
