package com.example.ms_product.Controller;

import com.example.ms_product.Model.Productos;
import com.example.ms_product.Service.ProductosService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;





@RestController
@RequestMapping("/api/v1/productos")
public class ProductosController {

    @Autowired
    private ProductosService service;

    @GetMapping("/{id}")
    public Productos buscarPorId(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public Productos guardarProductos(@RequestBody Productos producto){
        return service.saveProductos(producto);
    }

    @GetMapping
    public List<Productos> Listar() {
        return service.getProductos();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer id) {
    service.eliminarProducto(id);
    return ResponseEntity.noContent().build();
    }

    


}
