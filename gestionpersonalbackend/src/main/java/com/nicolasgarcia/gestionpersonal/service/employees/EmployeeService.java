package com.nicolasgarcia.gestionpersonal.service.employees;
import com.nicolasgarcia.gestionpersonal.dto.employee.EmployeesRequestDTO;
import com.nicolasgarcia.gestionpersonal.dto.employee.EmployeesResponseDTO;
import com.nicolasgarcia.gestionpersonal.dto.employee.EmployeesUpdateDTO;

import java.util.List;

public interface EmployeeService {
    EmployeesResponseDTO getById(Long id);
    List<EmployeesResponseDTO> getAll();
    List<EmployeesResponseDTO> getAllCategoria(Long id);
    EmployeesResponseDTO create(EmployeesRequestDTO requestDTO);
    void delete(Long id);
    EmployeesResponseDTO update(Long id,EmployeesUpdateDTO updateDTO);
}
