package com.nicolasgarcia.gestionpersonal.service.imp.usuario;
import com.nicolasgarcia.gestionpersonal.dto.usuario.UsuarioRequestDTO;
import com.nicolasgarcia.gestionpersonal.dto.usuario.UsuarioResponseDTO;
import com.nicolasgarcia.gestionpersonal.dto.usuario.UsuarioUpdateDTO;
import com.nicolasgarcia.gestionpersonal.dto.usuario.UsuarioUpdateMeDTO;
import com.nicolasgarcia.gestionpersonal.entity.Usuario;
import com.nicolasgarcia.gestionpersonal.exception.ResourceAlreadyExistsException;
import com.nicolasgarcia.gestionpersonal.exception.ResourceNotFoundExcepcion;
import com.nicolasgarcia.gestionpersonal.mapper.usuario.UsuarioMapper;
import com.nicolasgarcia.gestionpersonal.repository.usuario.UsuarioRepository;
import com.nicolasgarcia.gestionpersonal.security.CustomUserDetails;
import com.nicolasgarcia.gestionpersonal.service.usuario.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioServiceImp implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioServiceImp(UsuarioRepository usuarioRepository,
                              UsuarioMapper usuarioMapper){
        this.usuarioRepository=usuarioRepository;
        this.usuarioMapper=usuarioMapper;
    }

    // ============================
    // GET BY ID
    // ============================
    @Override
    public UsuarioResponseDTO getById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExcepcion("Usuario no encontrado" + id));
        return usuarioMapper.toDTO(usuario);
    }

    // ============================
    // GET ALL
    // ============================
    @Override
    public List<UsuarioResponseDTO> getAll(){
        List<Usuario> usuarioList = usuarioRepository.findAll();
        return usuarioList.stream()
                .map(usuarioMapper::toDTO)
                .toList();
    }

    // ============================
    // GET AUTHENTICATION
    // ============================
    @Override
    public UsuarioResponseDTO obtenerUsuarioActual(Authentication authentication){
        CustomUserDetails customUserDetails =(CustomUserDetails)authentication.getPrincipal();
        Usuario usuario = customUserDetails.getUsuario();
        return usuarioMapper.toDTO(usuario);
    }

    // ============================
    // UPDATE EMPLOYEES ME
    // ============================
    @Override
    public UsuarioResponseDTO acctualizarUsuarioActual(Authentication authentication,
                                                       UsuarioUpdateMeDTO dto){
        CustomUserDetails customUserDetails =(CustomUserDetails)authentication.getPrincipal();
        Usuario usuario = customUserDetails.getUsuario();
        if (usuarioRepository.existsByEmail(dto.getEmail())){
            throw new ResourceAlreadyExistsException(
                    "Ya existe un usuario con ese email"
            );
        }
        if(usuarioRepository.existsByUsername(dto.getUsername())){
            throw new ResourceAlreadyExistsException(
                    "Ya existe un usuario con ese username"
            );
        }
        usuarioMapper.updateMeEntity(dto,usuario);
        Usuario saved = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(saved);
    }

    // ============================
    // CREATE USUARIO
    // ============================
    @Override
    public UsuarioResponseDTO create(UsuarioRequestDTO requestDTO){
        Usuario usuario = usuarioMapper.toEntity(requestDTO);
        Usuario saved = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(saved);
    }

    // ============================
    // UPDATE EMPLOYEE
    // ============================
    @Override
    public UsuarioResponseDTO update(Long id, UsuarioUpdateDTO updateDTO){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExcepcion("Usuario no encontrado" + id));
        if (usuarioRepository.existsByEmail(updateDTO.getEmail())){
            throw new ResourceAlreadyExistsException(
                    "Ya existe un usuario con ese email"
            );
        }
        if(usuarioRepository.existsByUsername(updateDTO.getUsername())){
            throw new ResourceAlreadyExistsException(
                    "Ya existe un usuario con ese username"
            );
        }
        usuarioMapper.updateEntity(updateDTO,usuario);
        Usuario update = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(update);
    }



    // ============================
    // DELETE USUARIO
    // ============================
    @Override
    public void delete(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExcepcion("Usuario no encontrado: " + id));
        usuarioRepository.delete(usuario);
    }

}
