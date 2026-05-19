package com.example.ms_user.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuario")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false)
    private String nombre;

    // @JsonManagedReference sirve para controlar la serialización JSON en relaciones
    // bidireccionales y evitar bucles infinitos cuando se convierten entidades Java a JSON.
    @JsonManagedReference // Para impedir loops infinitos al preguntar por los usuarios
    
    // Relación 1:1 local (ambas tablas <perfil y usuarios> están en la misma db_usuarios)
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Perfil perfil;

    // Usamos @ElementCollection para crear una tabla intermedia 'usuario_juego_ids'
    // que solo guardará el ID del juego que está en el otro microservicio.
    @ElementCollection
    @CollectionTable(
            name = "usuario_producto_ids",
            joinColumns = @JoinColumn(name = "usuario_id")
    )
    @Column(name = "producto_id")
    private List<Integer>productosIds;
}