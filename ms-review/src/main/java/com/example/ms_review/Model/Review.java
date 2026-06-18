package com.example.ms_review.Model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "review")
public class Review {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;


@Column(name="usuario_id", nullable = false)
private Integer usuarioId;

@Column(name= "producto_id", nullable = false)
private Integer productoId;

@NotBlank
@Column(length = 1000)
private String comentario;

@Min(1)
@Max(5)
@Column(nullable = false)
private Integer puntuacion;
}
