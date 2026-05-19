package com.example.ms_cart.Client;

import com.example.ms_cart.Model.DTO.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// El name debe ser el spring.application.name del otro microservicio
@FeignClient(name = "ms-producto", url = "localhost:8082")

/* JuegoFeignClient es el "puente" que le permite al microservicio de Usuarios
comunicarse con el microservicio de Juegos usando HTTP,
pero como si fuera una llamada a un método Java. */

public interface ProductoFeignClient {
    // La respuesta que obtiene de ms-juegos la convierte
    // en JuegoDTO (Un objeto simple que representa los datos de
    // un juego que viajan entre microservicios, sin
    // exponer entidades JPA ni bases de datos.
    @GetMapping("/api/v1/productos/{id}")
    ProductoDTO obtenerProductoPorId(@PathVariable("id") Integer id);
}
