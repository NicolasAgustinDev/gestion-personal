package com.nicolasgarcia.gestionpersonal.dto.employee;

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
public class EmployeesRequestDTO {
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotNull
    private String dni;
    @NotBlank
    private String telefono;
    @NotBlank
    private String email;
    @NotNull
    private Long categoriaId;
}
