package com.example.ms_category.Model;

import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Aquí vinculamos tu variable 'nombre' con la columna 'name' de Laragon
    @Column(name = "name", nullable = false)
    private String name;

    @ElementCollection
    @CollectionTable(
            name = "categoria_producto_ids",
            joinColumns = @JoinColumn(name = "categoria_id")
    )
    @Column(name = "productos_id")
    private List<Integer>productosIds;
}

