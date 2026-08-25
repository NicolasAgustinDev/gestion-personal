package com.nicolasgarcia.gestionpersonal.mapper.employees;
import com.nicolasgarcia.gestionpersonal.dto.employee.EmployeesRequestDTO;
import com.nicolasgarcia.gestionpersonal.dto.employee.EmployeesResponseDTO;
import com.nicolasgarcia.gestionpersonal.dto.employee.EmployeesUpdateDTO;
import com.nicolasgarcia.gestionpersonal.entity.Categoria;
import com.nicolasgarcia.gestionpersonal.entity.Employee;
import org.springframework.stereotype.Component;
@Component
public class EmployeesMapper {
    public EmployeesResponseDTO toDTO(Employee employees) {
        if (employees == null) {
            return null;
        }
        return EmployeesResponseDTO.builder()
                .id(employees.getId())
                .nombre(employees.getNombre())
                .apellido(employees.getApellido())
                .dni(employees.getDni())
                .sueldo(employees.getCategoria().getSueldo())
                .categoriaId(
                        employees.getCategoria() !=null
                                ? employees.getCategoria().getId()
                                : null
                )
                .estado(employees.isEstado())
                .telefono(employees.getTelefono())
                .email(employees.getEmail())
                .fechaingreso(employees.getFechaingreso())
                .build();
    }
    
    public Employee toEntity(EmployeesRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Employee emp = new Employee();
        emp.setNombre(dto.getNombre());
        emp.setApellido(dto.getApellido());
        emp.setDni(dto.getDni());
        emp.setTelefono(dto.getTelefono());
        emp.setEmail(dto.getEmail());
        if(dto.getCategoriaId() !=null){
            Categoria categoria = new Categoria();
            categoria.setId(dto.getCategoriaId());
            emp.setCategoria(categoria);
        }
        return emp;
    }
    public Employee updateEmployee (EmployeesUpdateDTO updateDTO,
                                    Employee employee){
        if (updateDTO == null){
            return null;
        }
        employee.setNombre(updateDTO.getNombre());
        employee.setApellido(updateDTO.getApellido());
        employee.setDni(updateDTO.getDni());
        employee.setTelefono(updateDTO.getTelefono());
        employee.setEmail(updateDTO.getEmail());
        employee.setEstado(updateDTO.getEstado());
        if(updateDTO.getCategoriaId() != null ){
            Categoria categoria = new Categoria();
            categoria.setId(updateDTO.getCategoriaId());
            employee.setCategoria(categoria);
        }
        employee.setFechaingreso(updateDTO.getFechaingreso());
        return employee;
    }
}
