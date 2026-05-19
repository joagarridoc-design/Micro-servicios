package com.example.ms_review.Client;

import com.example.ms_review.Model.DTO.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-product", url = "localhost:8084")
public interface ProductFeignClient {

    
    @GetMapping("/api/v1/productos/{id}")
    ProductDTO obtenerProductoPorId(@PathVariable("id") Integer id);

}
