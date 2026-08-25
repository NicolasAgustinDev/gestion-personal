package com.nicolasgarcia.gestionpersonal.dto.Auth;

import com.nicolasgarcia.gestionpersonal.enums.usuario.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDTO {
    private long id;
    private String nombre;
    private Rol rol;
    private String token;
    private String tokenRefresh;
}
