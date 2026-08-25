package com.nicolasgarcia.gestionpersonal.mapper.categoria;
import com.nicolasgarcia.gestionpersonal.dto.categoria.CategoriaRequestDTO;
import com.nicolasgarcia.gestionpersonal.dto.categoria.CategoriaResponseDTO;
import com.nicolasgarcia.gestionpersonal.dto.categoria.CategoriaUpdateDTO;
import com.nicolasgarcia.gestionpersonal.entity.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {
    public CategoriaResponseDTO toDTO(Categoria categoria) {
        if (categoria == null) {
            return null;
        }
        return CategoriaResponseDTO.builder()
                .id(categoria.getId())
                .codigo(categoria.getCodigo())
                .descripcion(categoria.getDescripcion())
                .sueldo(categoria.getSueldo())
                .estado(categoria.isEstado())
                .build();
    }

    public Categoria updateEntity(CategoriaUpdateDTO updateDTO,
                                  Categoria categoria){
        if (updateDTO == null) {
            return null;
        }
        categoria.setCodigo(updateDTO.getCodigo());
        categoria.setDescripcion(updateDTO.getDescripcion());
        categoria.setSueldo(updateDTO.getSueldo());
        categoria.setEstado(updateDTO.getEstado());
        return categoria;
    }
    public Categoria toEntity(CategoriaRequestDTO dto ){
        if (dto == null) {
            return null;
        }
        Categoria cat = new Categoria();
        cat.setCodigo(dto.getCodigo());
        cat.setDescripcion(dto.getDescripcion());
        cat.setSueldo(dto.getSueldo());
        return cat;
    }
}
