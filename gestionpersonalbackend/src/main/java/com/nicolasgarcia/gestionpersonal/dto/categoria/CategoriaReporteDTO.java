package com.nicolasgarcia.gestionpersonal.dto.categoria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaReporteDTO {
    private Long totalCategorias;
    private Long totalCategoriasActivas;
    private Long totalCategoriasInactivas;
}
