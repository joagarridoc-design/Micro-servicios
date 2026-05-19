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
    
    @Column(name="tipo_pago", nullable = false)
    private String tipo;

    @Column(name="Monto", nullable = false)
    private String monto;

    @ElementCollection
    @CollectionTable(name = "payment_user", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "usuario_id")
    private List<Integer> UserIds;

}
