package com.example.ms_shipping.Controller;

import com.example.ms_shipping.Model.Shipping;
import com.example.ms_shipping.Service.ShippingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;


import java.util.List;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/envios")
@Tag(name = "Envíos", description ="Operaciones relacionadas con Envíos")
public class ShippingController {

    private final ShippingService service;

    public ShippingController(ShippingService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener",
        description = "Obtiene los Envíos por ID"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Envío encontrado exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Shipping.class))),
    @ApiResponse(responseCode = "404", description = "Envío no encontrada")
})
    public Shipping buscarPorId(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Guardar",
        description = "Guarda los Envíos entregados"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Envío guardado exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Shipping.class))),
    @ApiResponse(responseCode = "404", description = "Envío no procesado")
})
    public Shipping guardarEnvios(@RequestBody Shipping envios){
        return service.saveEnvios(envios);
    }

    @GetMapping
    @Operation(summary = "Obtener",
        description = "Obtiene todos los Envíos"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Envíos encontrados exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Shipping.class))),
    @ApiResponse(responseCode = "404", description = "No hay Envíos registrados")
})
    public List<Shipping> Listar() {
        return service.getEnvios();
    }
    

    @GetMapping("/mesinicio/{mesinicio}")
    @Operation(summary = "Obtener",
        description = "Obtiene los Envíos por mes de inicio"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Envío encontrado exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Shipping.class))),
    @ApiResponse(responseCode = "404", description = "Envío no encontrado")
})
    public List<Shipping> filtrarPorMesinicio(@PathVariable String mesinicio) {
        return service.getShippingByMesinicio(mesinicio);
    }
    
    @GetMapping("/mesllegada/{mesllegada}")
    @Operation(summary = "Obtener",
        description = "Obtiene los Envíos por Mes de llegada"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Envío encontrado exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Shipping.class))),
    @ApiResponse(responseCode = "404", description = "Envío no encontrado")
})
    public List<Shipping> filtrarPorMesllegada(@PathVariable String mesllegada) {
        return service.getShippingByMesllegada(mesllegada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar",
        description = "Elimina los Envíos por ID"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Envío eliminado exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Shipping.class))),
    @ApiResponse(responseCode = "404", description = "El envío no se pudo eliminar")
})
    public ResponseEntity<Void> eliminarEnvio(@PathVariable Integer id) {
    service.eliminarEnvio(id);
    return ResponseEntity.noContent().build();
    }
  @PutMapping("/{id}")
  @Operation (summary = "Actualizar Envío", description = "Actualizar envío por id ")
  @ApiResponse(responseCode = "200", description = "Envío actualizado exitosamente")
  @ApiResponse(responseCode = "404", description = "No se pudo actualizar el envío")
  public ResponseEntity<Shipping> actualizar(@PathVariable Integer id, @RequestBody Shipping envio) {
    return ResponseEntity.ok(service.update(id, envio));

  }
}





