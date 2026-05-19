package com.example.ms_cart.Model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

    @Column(name = "producto_id", nullable = false)
    private Integer productoId;

    @Column(nullable = false)
    private Integer cantidad;

    @ElementCollection
    @CollectionTable(
            name = "cart_producto_ids",
            joinColumns = @JoinColumn(name = "cart_id")
    )
    @Column(name = "producto_id")
    private List<Integer>productosIds;
}


