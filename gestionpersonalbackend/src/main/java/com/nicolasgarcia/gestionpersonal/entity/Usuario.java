package com.nicolasgarcia.gestionpersonal.entity;

import com.nicolasgarcia.gestionpersonal.enums.usuario.Rol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String apellido;
    private String username;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;
    private Boolean estado;

    @PrePersist
    public void prePersist() {
        this.estado = true;
    }
}
