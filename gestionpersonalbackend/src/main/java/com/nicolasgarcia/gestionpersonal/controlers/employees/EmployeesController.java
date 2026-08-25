package com.nicolasgarcia.gestionpersonal.controlers.employees;
import com.nicolasgarcia.gestionpersonal.dto.employee.EmployeesRequestDTO;
import com.nicolasgarcia.gestionpersonal.dto.employee.EmployeesResponseDTO;
import com.nicolasgarcia.gestionpersonal.dto.employee.EmployeesUpdateDTO;
import com.nicolasgarcia.gestionpersonal.service.imp.employees.EmployeeServiceImp;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class EmployeesController {

    private final EmployeeServiceImp employeesService;

    public EmployeesController(EmployeeServiceImp employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeesResponseDTO>> getAll(){
        return ResponseEntity.ok(employeesService.getAll());
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<EmployeesResponseDTO>> getAllCategoria(@PathVariable Long id){
        return ResponseEntity.ok(employeesService.getAllCategoria(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeesResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.employeesService.getById(id));
    }

    @PostMapping
    public ResponseEntity<EmployeesResponseDTO> create(@Valid @RequestBody
                                                      EmployeesRequestDTO requestDTO){
        EmployeesResponseDTO responseDTO = employeesService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.employeesService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<EmployeesResponseDTO> update(@PathVariable Long id,
                                                       @Valid @RequestBody
                                                       EmployeesUpdateDTO updateDTO) {
        return ResponseEntity.ok(employeesService.update(id,updateDTO));
    }
}
