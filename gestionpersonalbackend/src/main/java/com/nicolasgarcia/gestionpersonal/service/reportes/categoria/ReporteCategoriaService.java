package com.nicolasgarcia.gestionpersonal.service.reportes.categoria;

import java.util.concurrent.CompletableFuture;

public interface ReporteCategoriaService {
    CompletableFuture<Long> obtenerTotalCategorias();
    CompletableFuture<Long> obtenerCategoriasActivas();
    CompletableFuture<Long> obtenerCategoriasInactivas();
}
