package com.example.ms_inventario.Controller;

import com.example.ms_inventario.Model.Inventory;
import com.example.ms_inventario.Service.InventoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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
    @Operation(summary = "Buscar",
        description = "Busca los inventarios por id"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Producto eliminado exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Inventory.class))),
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
})
    
    public Inventory buscarPorId(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Guardar",
        description = "Guarda los inventarios creados"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Inventario creado exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Inventory.class))),
    @ApiResponse(responseCode = "404", description = "Inventario no creado")
})
    public Inventory guardarInventarios(@RequestBody Inventory producto){
        return service.saveInventarios(producto);
    }

    @GetMapping
    @Operation(summary = "Obtener",
        description = "Obtiene los inventarios"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Inventarios mostrados exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Inventory.class))),
    @ApiResponse(responseCode = "404", description = "Inventarios no creados")
})
    public List<Inventory> Listar() {
        return service.getInventarios();
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar",
        description = "Elimina Inventarios por ID"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Inventario eliminado exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Inventory.class))),
    @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
})
    public ResponseEntity<Void> eliminarInventario(@PathVariable Integer id) {
    service.eliminarInventario(id);
    return ResponseEntity.noContent().build();
    }



@PutMapping("/{id}")
@Operation(summary = "modifica Inventarios por ID", description = "modifica los Inventarios por su ID")
    public ResponseEntity<Inventory> actualizar(@PathVariable Integer id, @RequestBody Inventory invento) {
     try {
        Inventory invent = service.findById(id);
        invento.setIdinv(id);;
        invento.setMesinv(invento.getMesinv());
        invento.setNombreinv(invento.getNombreinv());
        invento.setProductoIds(invento.getProductoIds());

        service.save(invento);
        return ResponseEntity.ok(invent);
    } catch ( Exception e ) {
        return ResponseEntity.notFound().build();
    }
}

@Operation(
        summary = "Obtener inventarios por nombre",
        description = "Busca y retorna una lista de todos los inventarios que coincidan con el nombre proporcionado."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Búsqueda realizada con éxito (puede retornar una lista vacía si no hay coincidencias)"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Inventory>> obtenerPorNombre(@PathVariable String nombre) {
        List<Inventory> inventories = service.getInventariosByNombre(nombre);
        return ResponseEntity.ok(inventories);
    }

@Operation(
        summary = "Obtener inventarios por mes",
        description = "Busca y retorna una lista de todos los inventarios asociados al mes proporcionado (ej. Enero, Febrero)."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Búsqueda realizada con éxito"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/mes/{mes}")
    public ResponseEntity<List<Inventory>> obtenerPorMes(@PathVariable String mes) {
        List<Inventory> inventories = service.getInventariosByMes(mes);
        return ResponseEntity.ok(inventories);
    }
    
    


}
