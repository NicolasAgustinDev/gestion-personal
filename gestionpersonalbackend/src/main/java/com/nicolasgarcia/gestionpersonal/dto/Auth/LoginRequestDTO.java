package com.nicolasgarcia.gestionpersonal.dto.Auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDTO {
    @NotBlank(message = "El username no puede estar vacio")
    private String username;
    @NotBlank(message = "La contraseña no puede estar vacia")
    private String password;

}
