package com.example.ms_inventario.Model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idinv;
    
    private String nombreinv;
    private String mesinv;

    @ElementCollection
    @CollectionTable(name = "inventory_products", joinColumns = @JoinColumn(name = "inventory_id"))
    @Column(name = "product_id")
    private List<Integer> productoIds; 
}