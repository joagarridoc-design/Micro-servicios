package com.example.ms_review.Client;

import com.example.ms_review.Model.DTO.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "ms-user", url = "localhost:8081")


public interface UserFeignClient {
  
    @GetMapping("/api/v1/usuarios/{id}")
    UserDTO obtenerUserPorId(@PathVariable("id") Integer id);
}