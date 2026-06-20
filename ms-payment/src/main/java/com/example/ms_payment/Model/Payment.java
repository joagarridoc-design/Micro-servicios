package com.example.ms_payment.Model;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpago;
    
    @Column(name="tipo", nullable = false)
    private String tipo;

    @Column(name="monto", nullable = false)
    private String monto;

    @ElementCollection
    @CollectionTable(name = "payment_user", joinColumns = @JoinColumn(name = "payment_id"))
    @Column(name = "order_id")
    private List<Integer> userIds;

}
