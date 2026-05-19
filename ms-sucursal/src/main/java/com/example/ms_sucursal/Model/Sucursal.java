package com.example.ms_sucursal.Model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "sucursal")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idsuc;
    
    @Column(name="region", nullable = false)
    private String region;

    @Column(name="comuna", nullable = false)
    private String comuna;


    @ElementCollection
    @CollectionTable(name = "sucursal_inventory", joinColumns = @JoinColumn(name = "sucursal_id"))
    @Column(name = "inventory_id")
    private List<Integer> inventoryIds;



}
