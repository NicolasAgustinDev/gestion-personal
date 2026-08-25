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
    @NotBlank
    private String codigo;
    @NotBlank
    private String descripcion;
    @NotNull
    private BigDecimal sueldo;
}
