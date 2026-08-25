package com.nicolasgarcia.gestionpersonal.repository.employees;

import com.nicolasgarcia.gestionpersonal.entity.Categoria;
import com.nicolasgarcia.gestionpersonal.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeesRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByCategoriaId(Long id);
    boolean existsByCategoria_Id(Long id);
    Long countByCategoria(Categoria categoria);
}
