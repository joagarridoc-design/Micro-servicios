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
@CrossOrigin(origins = "http://localhost:8080")
public class CartController {

    @Autowired
    private CartService service;

    public CartController(CartService service) {
        this.service = service;
    }
    @GetMapping("/{id}")
    @Operation(summary = "Obtener por ID",
        description = "Obtiene los carritos por id"
    )
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "carrito encontrado exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Cart.class))),
    @ApiResponse(responseCode = "404", description = "carrito no encontrado")
})
    public Cart buscarPorId(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping
    @Operation(summary = "Obtener todos",
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
    @Operation(summary = "Obtener por Usuario",
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
    @Operation(summary = "Eliminar por ID",
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
@Operation(summary = "modifica Carritos por ID", description = "modifica los carritos por su ID")
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

@Operation(
        summary = "Obtener IDs de productos de un carrito",
        description = "Busca un carrito específico por su ID y devuelve únicamente la lista con los IDs de los productos contenidos en él."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de IDs de productos obtenida con éxito"),
        @ApiResponse(responseCode = "404", description = "Carrito no encontrado con el ID proporcionado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}/productos")
    public ResponseEntity<List<Integer>> obtenerProductosDeUnCarrito(@PathVariable Integer id) {
        List<Integer> productosIds = service.getProductosIdsByCartId(id);
        return ResponseEntity.ok(productosIds);
    }
    @PostMapping
    @Operation(summary = "Guardar Carritos",
        description = "Guarda los productos"
    )
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "carrito guardado exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Cart.class))),
    @ApiResponse(responseCode = "404", description = "carrito no se ha podido guardar")
})
    public Cart guardarProductos(@RequestBody Cart carrito){
        return service.saveCart(carrito);
    }
    

    



}


