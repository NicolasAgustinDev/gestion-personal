package com.nicolasgarcia.gestionpersonal.dto.Auth;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationRequestDTO {
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank(message = "El usuario no puede estar vacio")
    @Size(min = 5,max = 20, message = "El usuario debe tener entre 5 y 10 caracteres")
    private String username;
    @Email
    private String email;
    @NotBlank(message = "La contraseña no puede estar vacio")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).*$",
            message = "La contraseña debe contener mayúscula, minúscula, número y carácter especial"
    )
    private String password;
}
