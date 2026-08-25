package com.nicolasgarcia.gestionpersonal.security;
import com.nicolasgarcia.gestionpersonal.entity.Usuario;
import com.nicolasgarcia.gestionpersonal.mapper.auth.AuthMapper;
import com.nicolasgarcia.gestionpersonal.repository.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService  implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuario no encontrado"));
        return new CustomUserDetails(usuario);
        }
}
