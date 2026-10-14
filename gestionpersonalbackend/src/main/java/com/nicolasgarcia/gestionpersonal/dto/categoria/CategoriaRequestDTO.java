package com.nicolasgarcia.gestionpersonal.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaRequestDTO {
    @NotBlank(message = "El codigo es obligatorio")
    private String codigo;
    @NotBlank(message = "La descripcion es obligatorio")
    private String descripcion;
    @NotNull(message = "El sueldo es obligatorio")
    private BigDecimal sueldo;
}
