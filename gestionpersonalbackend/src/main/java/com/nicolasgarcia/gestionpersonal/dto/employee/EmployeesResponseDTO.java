package com.nicolasgarcia.gestionpersonal.dto.employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeesResponseDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private BigDecimal sueldo;
    private Long categoriaId;
    private String telefono;
    private String email;
    private LocalDate fechaingreso;
    private Boolean estado;
}
