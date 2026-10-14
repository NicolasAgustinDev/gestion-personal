package com.nicolasgarcia.gestionpersonal.dto.usuario;
import com.nicolasgarcia.gestionpersonal.enums.usuario.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioUpdateDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;
    @NotBlank(message = "El username es obligatorio")
    @Size(min = 5,max = 20, message = "El usuario debe tener entre 5 y 10 caracteres")
    private String username;
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email tiene que tener formato valido")
    private String email;
    @NotNull(message = "El Rol es obligatorio")
    private Rol rol;
    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;

}

