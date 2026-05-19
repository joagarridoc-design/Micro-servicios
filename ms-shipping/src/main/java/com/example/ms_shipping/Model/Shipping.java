package com.example.ms_shipping.Model;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idship;

    @Column(name="estado", nullable = false)
    private String estado;

    @Column(name="mesinicio", nullable = false)
    private String mesinicio;

    @Column(name="mesllegada", nullable = false)
    private String mesllegada;

    @ElementCollection
    @CollectionTable(name = "shipping_orders", joinColumns = @JoinColumn(name = "shipping_id"))
    @Column(name = "orders_id")
    private List<Integer> OrdersIds;
}
