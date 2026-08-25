package com.nicolasgarcia.gestionpersonal.service.imp.categoria;
import com.nicolasgarcia.gestionpersonal.dto.categoria.CategoriaRequestDTO;
import com.nicolasgarcia.gestionpersonal.dto.categoria.CategoriaResponseDTO;
import com.nicolasgarcia.gestionpersonal.dto.categoria.CategoriaUpdateDTO;
import com.nicolasgarcia.gestionpersonal.entity.Categoria;
import com.nicolasgarcia.gestionpersonal.exception.CategoriaNotFoundExeption;
import com.nicolasgarcia.gestionpersonal.mapper.categoria.CategoriaMapper;
import com.nicolasgarcia.gestionpersonal.repository.categoria.CategoriaRepository;
import com.nicolasgarcia.gestionpersonal.repository.employees.EmployeesRepository;
import com.nicolasgarcia.gestionpersonal.service.categoria.CategoriaService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaServiceImp implements CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final EmployeesRepository employeesRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaServiceImp(CategoriaRepository categoriaRepository,
                               CategoriaMapper categoriaMapper,EmployeesRepository employeesRepository){
        this.categoriaRepository=categoriaRepository;
        this.employeesRepository=employeesRepository;
        this.categoriaMapper=categoriaMapper;
    }
    // ============================
    // GET ALL
    // ============================
    @Override
    public List<CategoriaResponseDTO> getAll(){
        List<Categoria> categoriaList = categoriaRepository.findAll();
        return categoriaList.stream()
                .map(categoria -> {
                    CategoriaResponseDTO dto = categoriaMapper.toDTO(categoria);
                    Long cantidad =
                            employeesRepository
                                    .countByCategoria(
                                            categoria
                                    );
                    dto.setCantidadEmpleados(cantidad);
                    return dto;
                })
                .toList();
    }
    // ============================
    // GET BY ID
    // ============================
    @Override
    public  CategoriaResponseDTO getById(Long id){
        Categoria cat = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundExeption("Categoria no encontrado"));
        return categoriaMapper.toDTO(cat);
    }
    // ============================
    // CREATE CATEGORIA
    // ============================
    @Override
    public CategoriaResponseDTO create(CategoriaRequestDTO requestDTO){
        Categoria cat = categoriaMapper.toEntity(requestDTO);
        Categoria saved = categoriaRepository.save(cat);
        return categoriaMapper.toDTO(saved);
    }
    // ============================
    // UPDATE CATEGORIA
    // ============================
    @Override
    public CategoriaResponseDTO update(Long id, CategoriaUpdateDTO updateDTO){
        Categoria cat = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundExeption("Categoria no encontrado"));
        categoriaMapper.updateEntity(updateDTO,cat);
        Categoria update = categoriaRepository.save(cat);
        return categoriaMapper.toDTO(update);
    }
    // ============================
    // DELETE CATEGORIA
    // ============================
    @Override
    public void delete(Long id){
        Categoria cat = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundExeption("Categoria no encontrado"));
        if(employeesRepository.existsByCategoria_Id(id)){
            throw new RuntimeException("No se puede eliminar la categoría porque tiene empleados asociados");
        }
        categoriaRepository.delete(cat);
    }
}
