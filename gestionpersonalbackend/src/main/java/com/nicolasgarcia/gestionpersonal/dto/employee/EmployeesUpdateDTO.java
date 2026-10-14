package com.nicolasgarcia.gestionpersonal.dto.employee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeesUpdateDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;
    @NotBlank(message = "El DNI es obligatorio")
    private String dni;
    @NotBlank(message = "El telefono es obligatorio")
    private String telefono;
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email tiene que tener un formato valido")
    private String email;
    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;
    @NotNull(message = "La categoria es obligatorio")
    private Long categoriaId;
    @NotNull(message = "La fecha de ingreso es obligatorio")
    private LocalDate fechaingreso;
}
