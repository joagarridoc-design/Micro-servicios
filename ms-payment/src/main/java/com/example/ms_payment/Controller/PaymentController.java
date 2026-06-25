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
@CrossOrigin(origins = "http://localhost:8080")
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
    @Operation(summary = "Modificar productos por ID", description = "Modifica los productos por su ID")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "producto actualizado exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Payment.class))),
    @ApiResponse(responseCode = "404", description = "producto no encontrado")
})
    public ResponseEntity<Payment> actualizar(@PathVariable Integer id, @RequestBody Payment payment) {
     try {
        Payment Payment= service.findById(id);
        payment.setIdpago(id);
        payment.setMonto(payment.getMonto());
        payment.setIdpago(payment.getIdpago());
        payment.setTipo(payment.getTipo());
        payment.setUserIds(payment.getUserIds());
        

        service.save(payment);
        return ResponseEntity.ok(Payment);
    } catch ( Exception e ) {
        return ResponseEntity.notFound().build();
    }
    
}

@Operation(
        summary = "Buscar pago por monto",
        description = "Busca un pago específico utilizando su monto como parámetro de consulta."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago encontrado con éxito"),
        @ApiResponse(responseCode = "404", description = "No se encontró ningún pago con el monto proporcionado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/search")
    public ResponseEntity<Payment> obtenerPagoPorMonto(@RequestParam String monto) {
        Payment payment = service.getPaymentByMonto(monto);
        return ResponseEntity.ok(payment);
    }


    
    


}