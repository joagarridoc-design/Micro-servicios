package com.example.ms_product.Service;

import com.example.ms_product.Model.Productos;
import com.example.ms_product.Repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductosService {

    @Autowired
    private ProductosRepository repository;


    public Productos findById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public Productos saveProductos(Productos produ){
        return repository.save(produ);
    }

    public List<Productos>getProductos(){
        return repository.findAll();
    }
    public void eliminarProducto(Integer id) {
        repository.deleteById(id);
    }

   public Productos save(Productos orden) {
        
     
        
        return repository.save(orden);
    }

    public Productos getProductoByNombre(String nombre) {
        Productos producto = repository.findByNombre(nombre);
        
        if (producto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado con el nombre: " + nombre);
        }
        
        return producto;
    }
    

}
