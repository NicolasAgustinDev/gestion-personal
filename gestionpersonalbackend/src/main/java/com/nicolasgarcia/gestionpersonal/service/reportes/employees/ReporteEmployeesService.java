package com.nicolasgarcia.gestionpersonal.service.reportes.employees;

import java.util.concurrent.CompletableFuture;

public interface ReporteEmployeesService {
    CompletableFuture<Long> obtenerTotalEmpleados();
    CompletableFuture<Long> obtenerEmpleadosActivo();
    CompletableFuture<Long> obtenerEmpleadosInactivo();
}
