package com.example.ms_shipping.Controller;

import com.example.ms_shipping.Model.Shipping;
import com.example.ms_shipping.Service.ShippingService;
import org.springframework.http.ResponseEntity;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/envios")
public class ShippingController {

    @Autowired
    private ShippingService service;

    @GetMapping("/{id}")
    public Shipping buscarPorId(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public Shipping guardarEnvios(@RequestBody Shipping envios){
        return service.saveEnvios(envios);
    }

    @GetMapping
    public List<Shipping> Listar() {
        return service.getEnvios();
    }
    

    @GetMapping("/mesinicio/{mesinicio}")
    public List<Shipping> filtrarPorMesinicio(@PathVariable String mesinicio) {
        return service.getShippingByMesinicio(mesinicio);
    }
    
    @GetMapping("/mesllegada/{mesllegada}")
    public List<Shipping> filtrarPorMesllegada(@PathVariable String mesllegada) {
        return service.getShippingByMesllegada(mesllegada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEnvio(@PathVariable Integer id) {
    service.eliminarEnvio(id);
    return ResponseEntity.noContent().build();
    }
    


}


