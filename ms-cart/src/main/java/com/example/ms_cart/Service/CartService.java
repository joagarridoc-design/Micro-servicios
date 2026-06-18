package com.example.ms_cart.Service;

import com.example.ms_cart.Model.Cart;
import com.example.ms_cart.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository repository;

    public CartService(CartRepository repository) {
        this.repository = repository;
    }

     public Cart findById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public Cart saveCart(Cart cart) {
        return repository.save(cart);
    }

    public List<Cart> getAllCarts() {
        return repository.findAll();
    }

    public List<Cart> getCartByUsuario(Integer usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public void deleteItem(Integer id) {
        repository.deleteById(id);
    }

    @Transactional
    public void clearUsuarioCart(Integer usuarioId) {
        repository.deleteByUsuarioId(usuarioId);
    }

    public Cart save(Cart cart) {
        
     
        
        return repository.save(cart);
    }
    

}
