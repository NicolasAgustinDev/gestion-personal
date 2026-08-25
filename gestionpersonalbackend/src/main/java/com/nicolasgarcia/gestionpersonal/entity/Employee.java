package com.nicolasgarcia.gestionpersonal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String email;
    @ManyToOne
    @JoinColumn(name="categoria")
    private Categoria categoria;
    private LocalDate fechaingreso;
    private boolean estado;
    @PrePersist
    public void prePersist() {
        this.fechaingreso = LocalDate.now();
        this.estado = true;
    }
}
