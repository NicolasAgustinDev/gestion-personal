package com.nicolasgarcia.gestionpersonal.service.usuario;
import com.nicolasgarcia.gestionpersonal.dto.usuario.UsuarioRequestDTO;
import com.nicolasgarcia.gestionpersonal.dto.usuario.UsuarioResponseDTO;
import com.nicolasgarcia.gestionpersonal.dto.usuario.UsuarioUpdateDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UsuarioService {

    UsuarioResponseDTO getById(Long id);
    List<UsuarioResponseDTO> getAll();
    UsuarioResponseDTO create(UsuarioRequestDTO requestDTO);
    UsuarioResponseDTO update(Long id, UsuarioUpdateDTO updateDTO);
    void delete(Long id);
    UsuarioResponseDTO obtenerUsuarioActual(Authentication authentication);
    UsuarioResponseDTO acctualizarUsuarioActual(Authentication authentication,UsuarioUpdateDTO dto);
}
