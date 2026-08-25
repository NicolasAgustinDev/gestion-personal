package com.nicolasgarcia.gestionpersonal.repository.categoria;

import com.nicolasgarcia.gestionpersonal.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
}
