package com.example.ms_shipping.Client;

import com.example.ms_shipping.Model.DTO.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// El name debe ser el spring.application.name del otro microservicio
@FeignClient(name = "ms-order", url = "localhost:8082")

public interface OrderFeignClient {
    @GetMapping("/api/v1/ordenes/{id}")
    OrderDTO obtenerUsuarioPorId(@PathVariable("id") Integer id);
}

