package com.example.ms_order.Controller;

import com.example.ms_order.Model.Orders;
import com.example.ms_order.Service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ordenes")
@CrossOrigin(origins = "http://localhost:8080")
@Tag(name = "Órdenes", description ="Operaciones relacionadas con Órdenes")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/{id}")
    @Operation(summary = "Obtener por ID",
        description = "Obtiene las Órdenes por ID"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Órden encontrada exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Orders.class))),
    @ApiResponse(responseCode = "404", description = "Órden no encontrada")
})
    public Orders buscarPorId(@PathVariable Integer id) {
        return service.findByIdorden(id);
    }

    @PostMapping
    @Operation(summary = "Guardar",
        description = "Guardar las Órdenes específicadas"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Órden creada exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Orders.class))),
    @ApiResponse(responseCode = "404", description = "La Órden no se pudo crear")
})
    public Orders guardarOrdenes(@RequestBody Orders ordenes){
        return service.saveOrdenes(ordenes);
    }

    @GetMapping
    @Operation(summary = "Obtener",
        description = "Obtiene todas las Órdenes"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Órdenes encontradas exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Orders.class))),
    @ApiResponse(responseCode = "404", description = "No hay órdenes creadas")
})
    public List<Orders> Listar() {
        return service.getOrdenes();
    }
    

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Obtener",
        description = "Obtiene las Órdenes por Estado"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Órden encontrada exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Orders.class))),
    @ApiResponse(responseCode = "404", description = "Órden no encontrada")
})
    public List<Orders> filtrarPorEstado(@PathVariable String estado) {
        return service.getOrderByEstado(estado);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar",
        description = "Elimina las Órdenes por ID"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Órden eliminada exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Orders.class))),
    @ApiResponse(responseCode = "404", description = "Órden no encontrada")
})
    public ResponseEntity<Void> eliminarOrden(@PathVariable Integer id) {
    service.eliminarOrden(id);
    return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    @Operation(summary = "modifica Ordenes por ID", description = "modifica las ordenes por su ID")
    public ResponseEntity<Orders> actualizar(@PathVariable Integer id, @RequestBody Orders orden) {
     try {
        Orders order = service.findByIdorden(id);
        order.setIdorden(id);
        order.setEstado(orden.getEstado());
        order.setTotal(orden.getTotal());
        order.setUserIds(orden.getUserIds());

        service.save(orden);
        return ResponseEntity.ok(orden);
    } catch ( Exception e ) {
        return ResponseEntity.notFound().build();
    }
}

@Operation(
        summary = "Obtener el total de una orden por ID", 
        description = "Busca una orden específica mediante su ID y devuelve únicamente el valor de su campo 'total'."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Total de la orden recuperado exitosamente",
            content = @Content(
                mediaType = "text/plain",
                schema = @Schema(implementation = String.class, example = "150.50")
            )
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "No se encontró ninguna orden con el ID proporcionado",
            content = @Content
        )
    })
    @GetMapping("/{id}/total")
    public ResponseEntity<String> getTotalByOrderId(@PathVariable Integer id) {
        try {
            String total = service.obtenerTotalPorId(id);
            return ResponseEntity.ok(total);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
