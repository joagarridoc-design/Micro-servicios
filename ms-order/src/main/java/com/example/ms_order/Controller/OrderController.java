package com.example.ms_order.Controller;

import com.example.ms_order.Model.Orders;
import com.example.ms_order.Service.OrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ordenes")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/{id}")
    public Orders buscarPorId(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public Orders guardarOrdenes(@RequestBody Orders ordenes){
        return service.saveOrdenes(ordenes);
    }

    @GetMapping
    public List<Orders> Listar() {
        return service.getOrdenes();
    }
    

    @GetMapping("/estado/{estado}")
    public List<Orders> filtrarPorEstado(@PathVariable String estado) {
        return service.getOrderByEstado(estado);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOrden(@PathVariable Integer id) {
    service.eliminarOrden(id);
    return ResponseEntity.noContent().build();
    }


}
