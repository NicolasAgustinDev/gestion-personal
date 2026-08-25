package com.nicolasgarcia.gestionpersonal.controlers.usuario;
import com.nicolasgarcia.gestionpersonal.dto.categoria.CategoriaResponseDTO;
import com.nicolasgarcia.gestionpersonal.dto.categoria.CategoriaUpdateDTO;
import com.nicolasgarcia.gestionpersonal.dto.usuario.UsuarioRequestDTO;
import com.nicolasgarcia.gestionpersonal.dto.usuario.UsuarioResponseDTO;
import com.nicolasgarcia.gestionpersonal.dto.usuario.UsuarioUpdateDTO;
import com.nicolasgarcia.gestionpersonal.service.imp.usuario.UsuarioServiceImp;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UsuarioController {
    private final UsuarioServiceImp usuarioService;

    public UsuarioController(UsuarioServiceImp usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAll(){
        return ResponseEntity.ok(usuarioService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.getById(id));
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioResponseDTO> obtenerUsuarioActual(Authentication authentication){
        UsuarioResponseDTO usuario = usuarioService.obtenerUsuarioActual(authentication);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(@Valid @RequestBody
                                                     UsuarioRequestDTO requestDTO){
        UsuarioResponseDTO responseDTO = usuarioService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> update(@PathVariable Long id,
                                                       @Valid @RequestBody
                                                       UsuarioUpdateDTO updateDTO){
        return ResponseEntity.ok(usuarioService.update(id,updateDTO));
    }
    @PutMapping("/me")
    public ResponseEntity<UsuarioResponseDTO> updateMe(Authentication authentication,
                                                       @RequestBody UsuarioUpdateDTO dto){

    }
 }
