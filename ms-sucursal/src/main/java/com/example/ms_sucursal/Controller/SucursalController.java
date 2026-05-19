package com.example.ms_sucursal.Controller;

import com.example.ms_sucursal.Model.Sucursal;
import com.example.ms_sucursal.Service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService service;

    @PostMapping
    public ResponseEntity<Sucursal> saveSucursal(@RequestBody Sucursal sucursal) {
        Sucursal nuevaSucursal = service.saveSucursales(sucursal);
        return ResponseEntity.ok(nuevaSucursal);
    }

    @GetMapping
    public ResponseEntity<List<Sucursal>> getAllSucursales() {
        List<Sucursal> sucursales = service.getSucursales();
        return ResponseEntity.ok(sucursales);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> getSucursalById(@PathVariable("id") Integer id) {
        Sucursal sucursal = service.findById(id);
        if (sucursal != null) {
            return ResponseEntity.ok(sucursal);
        }
        return ResponseEntity.notFound().build(); // Devuelve error 404 si no existe
    }

    @GetMapping("/region/{region}")
    public ResponseEntity<List<Sucursal>> getByRegion(@PathVariable("region") String region) {
        List<Sucursal> sucursales = service.getSucursalByRegion(region);
        return ResponseEntity.ok(sucursales);
    }

    @GetMapping("/comuna/{comuna}")
    public ResponseEntity<List<Sucursal>> getByComuna(@PathVariable("comuna") String comuna) {
        List<Sucursal> sucursales = service.getSucursalesByNombre(comuna);
        return ResponseEntity.ok(sucursales);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSucursal(@PathVariable Integer id) {
    service.eliminarSucursal(id);
    return ResponseEntity.noContent().build();
    }

}
