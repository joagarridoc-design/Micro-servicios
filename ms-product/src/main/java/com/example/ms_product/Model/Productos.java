package com.example.ms_product.Model;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="nombre", nullable = false)
    private String nombre;

    @ElementCollection
    @CollectionTable(
            name = "producto_categorias_ids",
            joinColumns = @JoinColumn(name = "producto_id")
    )
    @Column(name = "categorias_id")
    private List<Integer>categoriasIds;

}
