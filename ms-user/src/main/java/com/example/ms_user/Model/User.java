package com.example.ms_user.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


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

    
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
    name = "usuario_producto_ids",
    joinColumns = @JoinColumn(name = "usuario_id")
    )

    @Column(name = "producto_id")
    private List<Integer>productosIds;
}