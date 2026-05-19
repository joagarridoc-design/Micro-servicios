package com.example.ms_order.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idorden;

    @Column(name="estado", nullable = false)
    private String estado;

    @Column(name="total", nullable = false)
    private String total;

    @ElementCollection
    @CollectionTable(name = "user_order", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "Users_id")
    private List<Integer> UserIds;
}

