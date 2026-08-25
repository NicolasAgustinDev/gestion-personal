package com.nicolasgarcia.gestionpersonal.dto.usuario;

import com.nicolasgarcia.gestionpersonal.enums.usuario.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponseDTO {
    private long id;
    private String nombre;
    private String apellido;
    private String username;
    private String email;
    private Rol rol;
    private Boolean estado;
}
