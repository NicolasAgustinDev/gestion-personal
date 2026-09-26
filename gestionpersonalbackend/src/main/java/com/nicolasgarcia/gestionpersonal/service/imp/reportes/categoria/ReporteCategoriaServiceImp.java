package com.nicolasgarcia.gestionpersonal.service.imp.reportes.categoria;

import com.nicolasgarcia.gestionpersonal.repository.categoria.CategoriaRepository;
import com.nicolasgarcia.gestionpersonal.service.reportes.categoria.ReporteCategoriaService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ReporteCategoriaServiceImp implements ReporteCategoriaService {
    private final CategoriaRepository categoriaRepository;

    public ReporteCategoriaServiceImp(CategoriaRepository categoriaRepository){
        this.categoriaRepository=categoriaRepository;
    }

    @Override
    @Async("reporteExecutor")
    public CompletableFuture<Long> obtenerTotalCategorias(){
        System.out.println(
                "Total ejecutado por: " +
                        Thread.currentThread().getName()
        );
        Long total = categoriaRepository.count();
        return CompletableFuture.completedFuture(total);
    }

    @Override
    @Async("reporteExecutor")
    public CompletableFuture<Long> obtenerCategoriasActivas(){
        System.out.println(
                "Total ejecutado por: " +
                        Thread.currentThread().getName()
        );
        Long totalActivos = categoriaRepository.countByEstado(true);
        return CompletableFuture.completedFuture(totalActivos);
    }

    @Override
    @Async("reporteExecutor")
    public CompletableFuture<Long> obtenerCategoriasInactivas(){
        System.out.println(
                "Total ejecutado por: " +
                        Thread.currentThread().getName()
        );
        Long totalInactivos = categoriaRepository.countByEstado(false);
        return CompletableFuture.completedFuture(totalInactivos);
    }


}
