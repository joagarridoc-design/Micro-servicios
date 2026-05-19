package com.example.ms_inventario.Controller;

import com.example.ms_inventario.Model.Inventory;
import com.example.ms_inventario.Service.InventoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;





@RestController
@RequestMapping("/api/v1/inventarios")
public class InventoryController {

    @Autowired
    private InventoryService service;

    @GetMapping("/{id}")
    public Inventory buscarPorId(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public Inventory guardarInventarios(@RequestBody Inventory producto){
        return service.saveInventarios(producto);
    }

    @GetMapping
    public List<Inventory> Listar() {
        return service.getInventarios();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInventario(@PathVariable Integer id) {
    service.eliminarInventario(id);
    return ResponseEntity.noContent().build();
    }
    
    


}
