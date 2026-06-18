package com.example.ms_cart.Controller;

import com.example.ms_cart.Model.Cart;
import com.example.ms_cart.Service.CartService;

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
@RequestMapping("/api/v1/cart")
public class CartController {

    @Autowired
    private CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Obtener",
        description = "Obtiene los carritos"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Carrito mostrado exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Cart.class))),
    @ApiResponse(responseCode = "404", description = "No hay carritos para mostrar")
})
    public List<Cart> listarTodas() {
        return service.getAllCarts();
    }

    public ResponseEntity<Cart> agregarAlCarrito(@RequestBody Cart cart) {
        Cart guardado = service.saveCart(cart);
        return ResponseEntity.status(201).body(guardado);
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Obtener",
        description = "Obtiene el carrito por el ID de Usuario"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Carrito encontrado exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Cart.class))),
    @ApiResponse(responseCode = "404", description = "Carrito no encontrado")
})
    public List<Cart> obtenerCarritoUsuario(@PathVariable Integer usuarioId) {
        return service.getCartByUsuario(usuarioId);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar",
        description = "Elimina los carritos por id"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Carrito eliminado exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Cart.class))),
    @ApiResponse(responseCode = "404", description = "Carrito no encontrado")
})
    public void eliminarItem(@PathVariable Integer id) {
        service.deleteItem(id);
    }




@PutMapping("/{id}")
@Operation(summary = "modifica Ordenes por ID", description = "modifica las ordenes por su ID")
    public ResponseEntity<Cart> actualizar(@PathVariable Integer id, @RequestBody Cart cart) {
     try {
        Cart car = service.findById(id);
        cart.setId(id);
        cart.setCantidad(cart.getCantidad());
        cart.setProductoId(cart.getProductoId());
        cart.setProductosIds(cart.getProductosIds());
        cart.setUsuarioId(cart.getUsuarioId());

        service.save(cart);
        return ResponseEntity.ok(car);
    } catch ( Exception e ) {
        return ResponseEntity.notFound().build();
    }
}

}
