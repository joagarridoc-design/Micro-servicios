package com.example.ms_inventario.Client;

import com.example.ms_inventario.Model.DTO.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// El name debe ser el spring.application.name del otro microservicio
@FeignClient(name = "ms-producto", url = "localhost:8082")



public interface ProductoFeignClient {
    
    @GetMapping("/api/v1/Productos/{id}")
    ProductoDTO obtenerproductoPorId(@PathVariable("id") Integer id);
}

