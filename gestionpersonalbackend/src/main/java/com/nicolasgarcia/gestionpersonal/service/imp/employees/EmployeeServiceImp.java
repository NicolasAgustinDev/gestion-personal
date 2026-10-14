package com.nicolasgarcia.gestionpersonal.service.imp.employees;
import com.nicolasgarcia.gestionpersonal.dto.employee.EmployeesReporteDTO;
import com.nicolasgarcia.gestionpersonal.dto.employee.EmployeesRequestDTO;
import com.nicolasgarcia.gestionpersonal.dto.employee.EmployeesResponseDTO;
import com.nicolasgarcia.gestionpersonal.dto.employee.EmployeesUpdateDTO;
import com.nicolasgarcia.gestionpersonal.entity.Employee;
import com.nicolasgarcia.gestionpersonal.exception.ResourceAlreadyExistsException;
import com.nicolasgarcia.gestionpersonal.exception.ResourceNotFoundExcepcion;
import com.nicolasgarcia.gestionpersonal.mapper.employees.EmployeesMapper;
import com.nicolasgarcia.gestionpersonal.repository.employees.EmployeesRepository;
import com.nicolasgarcia.gestionpersonal.service.employees.EmployeeService;
import com.nicolasgarcia.gestionpersonal.service.reportes.employees.ReporteEmployeesService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.*;

@Service
public class EmployeeServiceImp implements EmployeeService {
    private final EmployeesRepository employeesRepository;
    private final EmployeesMapper employeesMapper;
    private final ReporteEmployeesService reporteEmployeesService;

    public EmployeeServiceImp(EmployeesRepository employeesRepository,
                              EmployeesMapper employeesMapper,
                              ReporteEmployeesService reporteEmployeesService){
        this.employeesRepository=employeesRepository;
        this.employeesMapper=employeesMapper;
        this.reporteEmployeesService = reporteEmployeesService;
    }
    // ============================
    // GET BY ID
    // ============================
    @Override
    public EmployeesResponseDTO getById(Long id) {
        Employee emp = employeesRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundExcepcion("Empleado no encontrado" + id));
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
        if (employeesRepository.existsByDni(requestDTO.getDni())){
            throw new ResourceAlreadyExistsException(
                    "Ya existe un empleado con ese DNI"
            );
        }
        if(employeesRepository.existsByEmail(requestDTO.getEmail())){
            throw new ResourceAlreadyExistsException(
                    "Ya existe un empleado con ese email"
            );
        }
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
                .orElseThrow(() -> new ResourceNotFoundExcepcion("Empleado no encontrado: " + id));
        employeesRepository.delete(emp);
    }

    // ============================
    // UPDATE EMPLOYEES
    // ============================
    @Override
    public EmployeesResponseDTO update(Long id, EmployeesUpdateDTO updateDTO){
        // 1️⃣ Buscar empleado existente
        Employee emp = employeesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExcepcion("Empleado no encontrado"));
        if(employeesRepository.existsByDni(updateDTO.getDni())){
            throw new ResourceAlreadyExistsException(
                    "Ya existe un empleado con ese DNI"
            );
        };
        employeesMapper.updateEmployee(updateDTO, emp);
        Employee update = employeesRepository.save(emp);
        return employeesMapper.toDTO(update);
    }
    // ============================
    // GENERAR REPORTES
    // ============================
    @Override
    public EmployeesReporteDTO generarReporte() throws ExecutionException, InterruptedException {
        CompletableFuture<Long> total =
                reporteEmployeesService.obtenerTotalEmpleados();
        CompletableFuture<Long> activos =
                reporteEmployeesService.obtenerEmpleadosActivo();
        CompletableFuture<Long> inactivos =
                reporteEmployeesService.obtenerEmpleadosInactivo();
        CompletableFuture.allOf(
                total,
                activos,
                inactivos
        ).join();
        return new EmployeesReporteDTO(
                total.get(),
                activos.get(),
                inactivos.get()
        );
    }


}