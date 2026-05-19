package com.example.ms_sucursal.Client;
import com.example.ms_sucursal.Model.DTO.InventoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// El name debe ser el spring.application.name del otro microservicio
@FeignClient(name = "ms-inventory", url = "localhost:8082")



public interface InventoryFeignClient {
    
    @GetMapping("/api/v1/inventarios/{id}")
    InventoryDTO obtenerInventarioPorId(@PathVariable("id") Integer id);
}
