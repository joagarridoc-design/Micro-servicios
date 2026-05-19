package com.example.ms_cart.Controller;

import com.example.ms_cart.Model.Cart;
import com.example.ms_cart.Service.CartService;
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
    public List<Cart> listarTodas() {
        return service.getAllCarts();
    }

    public ResponseEntity<Cart> agregarAlCarrito(@RequestBody Cart cart) {
        Cart guardado = service.saveCart(cart);
        return ResponseEntity.status(201).body(guardado);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Cart> obtenerCarritoUsuario(@PathVariable Integer usuarioId) {
        return service.getCartByUsuario(usuarioId);
    }

    @DeleteMapping("/{id}")
    public void eliminarItem(@PathVariable Integer id) {
        service.deleteItem(id);
    }

}
