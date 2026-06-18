package com.example.ms_payment.Controller;

import com.example.ms_payment.Model.Payment;
import com.example.ms_payment.Service.PaymentService;

import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/v1/pagos")
@Tag(name = "Pagos", description ="Operaciones relacionadas con los Pagos")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @GetMapping("/{id}")
    @Operation(summary = "Obtener",
        description = "Obtiene los Pagos por ID"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Pago encontrado exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Payment.class))),
    @ApiResponse(responseCode = "404", description = "Pago no encontrado")
})
    public Payment buscarPorId(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Guardar",
        description = "Guarda los pagos especificados"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Pago creado exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Payment.class))),
    @ApiResponse(responseCode = "404", description = "El pago no se ha podido crear")
})
    public Payment guardarPagos(@RequestBody Payment pagos){
        return service.savePagos(pagos);
    }

    @GetMapping
    @Operation(summary = "Obtener",
        description = "Obtiene todos los Pagos"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Pagos encontrados exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Payment.class))),
    @ApiResponse(responseCode = "404", description = "Pago no encontrado")
})
    public List<Payment> Listar() {
        return service.getPagos();
    }
    

    @GetMapping("/tipo/{tipo}")
    @Operation(summary = "Obtener",
        description = "Obtiene los Pagos por tipo"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Pago encontrado exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Payment.class))),
    @ApiResponse(responseCode = "404", description = "Pago no encontrado")
})
    public List<Payment> filtrarPorTipo(@PathVariable String tipo) {
        return service.getPaymentsByTipo(tipo);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar",
        description = "Elimina los Pagos por ID"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Pago eliminado exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Payment.class))),
    @ApiResponse(responseCode = "404", description = "Pago no encontrado")
})
    public ResponseEntity<Void> eliminarPago(@PathVariable Integer id) {
    service.eliminarPago(id);
    return ResponseEntity.noContent().build();
    }


 @PutMapping("/{id}")
    @Operation(summary = "modifica pagos por ID", description = "modifica los pagos por su ID")
    public ResponseEntity<Payment> actualizar(@PathVariable Integer id, @RequestBody Payment pay) {
     try {
        Payment payment  = service.findById(id);
        payment.setIdpago(id);
        payment.setTipo(pay.getTipo());
        payment.setMonto(pay.getMonto());
        payment.setUserIds(pay.getUserIds());
        


        service.save(payment);
        return ResponseEntity.ok(pay);
    } catch ( Exception e ) {
        return ResponseEntity.notFound().build();
    }
}
    
    


}