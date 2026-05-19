package com.example.ms_order.Client;

import com.example.ms_order.Model.DTO.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// El name debe ser el spring.application.name del otro microservicio
@FeignClient(name = "ms-usuarios", url = "localhost:8082")



public interface UserFeignClient {
    
    @GetMapping("/api/v1/usuarios/{id}")
    UserDTO obtenerUsuariosPorId(@PathVariable("id") Integer id);
}