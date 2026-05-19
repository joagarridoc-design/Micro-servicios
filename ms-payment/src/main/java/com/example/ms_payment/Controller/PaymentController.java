package com.example.ms_payment.Controller;

import com.example.ms_payment.Model.Payment;
import com.example.ms_payment.Service.PaymentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pagos")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @GetMapping("/{id}")
    public Payment buscarPorId(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public Payment guardarPagos(@RequestBody Payment pagos){
        return service.savePagos(pagos);
    }

    @GetMapping
    public List<Payment> Listar() {
        return service.getPagos();
    }
    

    @GetMapping("/tipo/{tipo}")
    public List<Payment> filtrarPorTipo(@PathVariable String tipo) {
        return service.getPaymentsByTipo(tipo);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Integer id) {
    service.eliminarPago(id);
    return ResponseEntity.noContent().build();
    }
    
    


}