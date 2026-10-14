package com.nicolasgarcia.gestionpersonal.dto.usuario;
import com.nicolasgarcia.gestionpersonal.enums.usuario.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;
    @NotBlank(message = "El username es obligatorio")
    private String username;
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email tiene que tener un formato valido")
    private String email;
    @NotBlank
    private String password;
    @NotNull
    private Rol rol;
}
