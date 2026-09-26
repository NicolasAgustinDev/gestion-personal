package com.nicolasgarcia.gestionpersonal.service.imp.reportes.employees;
import com.nicolasgarcia.gestionpersonal.repository.employees.EmployeesRepository;
import com.nicolasgarcia.gestionpersonal.service.reportes.employees.ReporteEmployeesService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;
@Service
public class ReporteEmployeesServiceImp implements ReporteEmployeesService {
    private final EmployeesRepository employeesRepository;

    public ReporteEmployeesServiceImp(EmployeesRepository employeesRepository){
        this.employeesRepository=employeesRepository;
    }

    @Override
    @Async("reporteExecutor")
    public CompletableFuture<Long> obtenerTotalEmpleados(){
        System.out.println(
                "Total ejecutado por: " +
                        Thread.currentThread().getName()
        );
        Long total = employeesRepository.count();
        return CompletableFuture.completedFuture(total);
    }

    @Override
    @Async("reporteExecutor")
    public CompletableFuture<Long> obtenerEmpleadosActivo(){
        System.out.println(
                "Total ejecutado por: " +
                        Thread.currentThread().getName()
        );
        Long empleadosActivos = employeesRepository.countByEstado(true);
        return CompletableFuture.completedFuture(empleadosActivos);
    }

    @Override
    @Async("reporteExecutor")
    public CompletableFuture<Long> obtenerEmpleadosInactivo(){
        System.out.println(
                "Total ejecutado por: " +
                        Thread.currentThread().getName()
        );
        Long empleadosInactivos = employeesRepository.countByEstado(false);
        return CompletableFuture.completedFuture(empleadosInactivos);
    }



}
