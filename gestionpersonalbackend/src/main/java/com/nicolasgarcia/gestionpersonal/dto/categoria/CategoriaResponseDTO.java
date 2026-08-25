package com.nicolasgarcia.gestionpersonal.dto.categoria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaResponseDTO {
    private long id;
    private String codigo;
    private String descripcion;
    private BigDecimal sueldo;
    private Boolean estado;
    private Long cantidadEmpleados;
}
