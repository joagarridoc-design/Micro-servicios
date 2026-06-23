package com.example.ms_product.Controller;

import com.example.ms_product.Model.Productos;
import com.example.ms_product.Service.ProductosService;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;





@RestController
@RequestMapping("/api/v1/productos")
@Tag(name = "Productos", description ="Operaciones relacionadas con productos")
public class ProductosController {

    @Autowired
    private ProductosService service;

    @GetMapping("/{id}")
    @Operation(summary = "Obtener por ID",
        description = "Obtiene los productos por id"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "producto encontrado exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Productos.class))),
    @ApiResponse(responseCode = "404", description = "producto no encontrada")
})
    public Productos buscarPorId(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Guardar productos",
        description = "Guarda los productos"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Producto guardado exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Productos.class))),
    @ApiResponse(responseCode = "404", description = "producto no se ha podido guardar")
})
    public Productos guardarProductos(@RequestBody Productos producto){
        return service.saveProductos(producto);
    }

    @GetMapping
    @Operation(summary = "Obtener productos",
        description = "Obtiene los productos"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "productos encontrados exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Productos.class))),
    @ApiResponse(responseCode = "404", description = "No hay Productos encontrados")
})
    public List<Productos> Listar() {
        return service.getProductos();
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar productos",
        description = "Elimina los productos por id"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Producto eliminado exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Productos.class))),
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
})
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer id) {
    service.eliminarProducto(id);
    return ResponseEntity.noContent().build();
    }
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "producto eliminado exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Productos.class))),
    @ApiResponse(responseCode = "404", description = "producto no encontrado")
})
    public ResponseEntity<Void> eliminarOrden(@PathVariable Integer id) {
    service.eliminarProducto(id);
    return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    @Operation(summary = "Modificar productos por ID", description = "Modifica los productos por su ID")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "producto actualizado exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Productos.class))),
    @ApiResponse(responseCode = "404", description = "producto no encontrado")
})
    public ResponseEntity<Productos> actualizar(@PathVariable Integer id, @RequestBody Productos producto) {
     try {
        Productos Producto= service.findById(id);
        producto.setId(id);
        producto.setNombre(producto.getNombre());
        producto.setCategoriasIds(producto.getCategoriasIds());
        

        service.save(producto);
        return ResponseEntity.ok(producto);
    } catch ( Exception e ) {
        return ResponseEntity.notFound().build();
    }
    
}

@Operation(
        summary = "Buscar producto por nombre",
        description = "Busca un producto específico utilizando su nombre como parámetro de consulta."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado con éxito"),
        @ApiResponse(responseCode = "404", description = "No se encontró ningún producto con el nombre proporcionado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/search")
    public ResponseEntity<Productos> obtenerProductoPorNombre(@RequestParam String nombre) {
        Productos producto = service.getProductoByNombre(nombre);
        return ResponseEntity.ok(producto);
    }

    


}
