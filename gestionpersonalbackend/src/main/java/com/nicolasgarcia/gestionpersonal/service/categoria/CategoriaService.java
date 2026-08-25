package com.nicolasgarcia.gestionpersonal.service.categoria;

import com.nicolasgarcia.gestionpersonal.dto.categoria.CategoriaRequestDTO;
import com.nicolasgarcia.gestionpersonal.dto.categoria.CategoriaResponseDTO;
import com.nicolasgarcia.gestionpersonal.dto.categoria.CategoriaUpdateDTO;

import java.util.List;

public interface CategoriaService {
    List<CategoriaResponseDTO> getAll();
    CategoriaResponseDTO getById(Long id);
    CategoriaResponseDTO create(CategoriaRequestDTO requestDTO);
    void delete(Long id);
    CategoriaResponseDTO update(Long id, CategoriaUpdateDTO updateDTO);
}
