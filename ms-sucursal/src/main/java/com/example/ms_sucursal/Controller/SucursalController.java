package com.example.ms_sucursal.Controller;

import com.example.ms_sucursal.Model.Sucursal;
import com.example.ms_sucursal.Service.SucursalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sucursales")
@CrossOrigin(origins = "http://localhost:8080")
public class SucursalController {

    @Autowired
    private SucursalService service;

    @PostMapping
    @Operation(summary = "Guardar",
        description = "Guarda la sucursal creada"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Sucursal creada exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Sucursal.class))),
    @ApiResponse(responseCode = "404", description = "Sucursal no creada")
})
    public ResponseEntity<Sucursal> saveSucursal(@RequestBody Sucursal sucursal) {
        Sucursal nuevaSucursal = service.saveSucursales(sucursal);
        return ResponseEntity.ok(nuevaSucursal);
    }

    @GetMapping
    @Operation(summary = "Obtener",
        description = "Obtiene todas las sucursales"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Sucursales mostradas exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Sucursal.class))),
    @ApiResponse(responseCode = "404", description = "No hay sucursales para mostrar")
})
    public ResponseEntity<List<Sucursal>> getAllSucursales() {
        List<Sucursal> sucursales = service.getSucursales();
        return ResponseEntity.ok(sucursales);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener",
        description = "Obtiene las sucursales por id"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Sucursal encontrada exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Sucursal.class))),
    @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
})
    public ResponseEntity<Sucursal> getSucursalById(@PathVariable("id") Integer id) {
        Sucursal sucursal = service.findById(id);
        if (sucursal != null) {
            return ResponseEntity.ok(sucursal);
        }
        return ResponseEntity.notFound().build(); 
    }

    @GetMapping("/region/{region}")
    @Operation(summary = "Obtener",
        description = "Obtiene las sucursales por Region"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Sucursal encontrada exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Sucursal.class))),
    @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
})
    public ResponseEntity<List<Sucursal>> getByRegion(@PathVariable("region") String region) {
        List<Sucursal> sucursales = service.getSucursalByRegion(region);
        return ResponseEntity.ok(sucursales);
    }

    @GetMapping("/comuna/{comuna}")
    @Operation(summary = "Obtener",
        description = "Obtiene las sucursales por el nombre de la comuna"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Sucursal encontrada exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Sucursal.class))),
    @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
})
    public ResponseEntity<List<Sucursal>> getByComuna(@PathVariable("comuna") String comuna) {
        List<Sucursal> sucursales = service.getSucursalesByNombre(comuna);
        return ResponseEntity.ok(sucursales);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar",
        description = "Elimina la sucursal por ID"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Sucursal eliminada exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Sucursal.class))),
    @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
})
    public ResponseEntity<Void> eliminarSucursal(@PathVariable Integer id) {
    service.eliminarSucursal(id);
    return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "modifica sucursales por ID", description = "modifica las sucursales por su ID")
    public ResponseEntity<Sucursal> actualizar(@PathVariable Integer id, @RequestBody Sucursal sucursal) {
     try {
        Sucursal suc = service.findById(id);
        suc.setIdsuc(id);
        suc.setRegion(sucursal.getRegion());
        suc.setComuna(sucursal.getComuna());
        suc.setInventoryIds(sucursal.getInventoryIds());

        service.save(suc);
        return ResponseEntity.ok(sucursal);
    } catch ( Exception e ) {
        return ResponseEntity.notFound().build();
    }
}

}
