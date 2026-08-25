package com.nicolasgarcia.gestionpersonal.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categoria")
@Data
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long id;
    private String codigo;
    private String descripcion;
    private BigDecimal sueldo;
    private boolean estado;
    //relacion
    @OneToMany(mappedBy = "categoria")
    @JsonIgnore
    private List<Employee> employees;

    @PrePersist
    public void prePersist() {
        this.estado = true;
    }
}
