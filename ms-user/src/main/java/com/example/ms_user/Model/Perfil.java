package com.example.ms_user.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="perfil")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Boolean activo;

    // Se usa para romper la serialización recursiva infinita en relaciones bidireccionales
    // cuando se convierten objetos Java a JSON.
    @JsonBackReference // Para no volver al Usuario y generar Loops
    @OneToOne
    @JoinColumn(name = "usuario_id") // FK hacia la tabla usuario
    private User usuario;
}
