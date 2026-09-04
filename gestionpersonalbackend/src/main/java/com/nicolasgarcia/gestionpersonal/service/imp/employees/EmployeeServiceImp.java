package com.nicolasgarcia.gestionpersonal.service.imp.employees;
import com.nicolasgarcia.gestionpersonal.dto.employee.EmployeesRequestDTO;
import com.nicolasgarcia.gestionpersonal.dto.employee.EmployeesResponseDTO;
import com.nicolasgarcia.gestionpersonal.dto.employee.EmployeesUpdateDTO;
import com.nicolasgarcia.gestionpersonal.entity.Categoria;
import com.nicolasgarcia.gestionpersonal.entity.Employee;
import com.nicolasgarcia.gestionpersonal.exception.EmployeesNotFoundExeption;
import com.nicolasgarcia.gestionpersonal.exception.GlobalExceptionHandler;
import com.nicolasgarcia.gestionpersonal.mapper.employees.EmployeesMapper;
import com.nicolasgarcia.gestionpersonal.repository.employees.EmployeesRepository;
import com.nicolasgarcia.gestionpersonal.service.employees.EmployeeService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {
    private final EmployeesRepository employeesRepository;
    private final EmployeesMapper employeesMapper;

    public EmployeeServiceImp(EmployeesRepository employeesRepository,
                              EmployeesMapper employeesMapper){
        this.employeesRepository=employeesRepository;
        this.employeesMapper=employeesMapper;
    }
    // ============================
    // GET BY ID
    // ============================
    @Override
    public EmployeesResponseDTO getById(Long id) {
        Employee emp = employeesRepository.findById(id)
                .orElseThrow(() ->  new EmployeesNotFoundExeption("Empleado no encontrado"));
        return employeesMapper.toDTO(emp);
    }
    // ============================
    // GET ALL
    // ============================
    @Override
    public List<EmployeesResponseDTO> getAll(){
        List<Employee> employeesList = employeesRepository.findAll();
        return employeesList.stream()
                .map(employeesMapper::toDTO)
                .toList();
    }
    // ============================
    // GET ALL Categoria
    // ============================
    @Override
    public List<EmployeesResponseDTO> getAllCategoria(Long id){
        List<Employee> employeesListCategoria = employeesRepository.findByCategoriaId(id);
        return employeesListCategoria.stream()
                .map(employeesMapper::toDTO)
                .toList();
    }
    // ============================
    // CREATE EMPLOYEES
    // ============================
    @Override
    public EmployeesResponseDTO create(EmployeesRequestDTO requestDTO){
        Employee employees = employeesMapper.toEntity(requestDTO);
        Employee saved = employeesRepository.save(employees);
        return employeesMapper.toDTO(saved);
    }
    // ============================
    // DELETE EMPLOYEES
    // ============================
    @Override
    public void delete(Long id) {
        Employee emp = employeesRepository.findById(id)
                .orElseThrow(() -> new EmployeesNotFoundExeption("Employee not found with id: " + id));
        employeesRepository.delete(emp);
    }

    // ============================
    // UPDATE EMPLOYEES
    // ============================
    @Override
    public EmployeesResponseDTO update(Long id, EmployeesUpdateDTO updateDTO){
        // 1️⃣ Buscar empleado existente
        Employee emp = employeesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        employeesMapper.updateEmployee(updateDTO, emp);
        Employee update = employeesRepository.save(emp);
        return employeesMapper.toDTO(update);
    }
}