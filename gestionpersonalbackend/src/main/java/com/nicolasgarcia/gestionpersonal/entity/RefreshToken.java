package com.nicolasgarcia.gestionpersonal.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "refresh_token")
@Data
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String token;
    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;
    private LocalDateTime fechaExpiracion;
    private Boolean revocado;
}
