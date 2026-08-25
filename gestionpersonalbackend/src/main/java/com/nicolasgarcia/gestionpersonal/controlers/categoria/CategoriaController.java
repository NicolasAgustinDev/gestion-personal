package com.nicolasgarcia.gestionpersonal.controlers.categoria;
import com.nicolasgarcia.gestionpersonal.dto.categoria.CategoriaRequestDTO;
import com.nicolasgarcia.gestionpersonal.dto.categoria.CategoriaResponseDTO;
import com.nicolasgarcia.gestionpersonal.dto.categoria.CategoriaUpdateDTO;
import com.nicolasgarcia.gestionpersonal.service.imp.categoria.CategoriaServiceImp;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "http://127.0.0.1:5500")

public class CategoriaController {

    private final CategoriaServiceImp categoriaService;

    public CategoriaController(CategoriaServiceImp categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> getAll(){
        return ResponseEntity.ok(categoriaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.categoriaService.getById(id));
    }
    @PostMapping
    public  ResponseEntity<CategoriaResponseDTO> create(@Valid @RequestBody
                                                        CategoriaRequestDTO requestDTO){
        CategoriaResponseDTO responseDTO = categoriaService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> update(@PathVariable Long id,
                                                       @Valid @RequestBody
                                                       CategoriaUpdateDTO updateDTO){
        return ResponseEntity.ok(categoriaService.update(id,updateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
