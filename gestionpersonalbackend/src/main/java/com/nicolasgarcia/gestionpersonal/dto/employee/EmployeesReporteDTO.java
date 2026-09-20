package com.nicolasgarcia.gestionpersonal.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeesReporteDTO {
    private Long totalEmpleados;
    private Long totalEmpleadosActivos;
    private Long totalEmpleadosInactivos;
}
