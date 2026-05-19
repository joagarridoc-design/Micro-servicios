package com.example.ms_cart.Repository;

import com.example.ms_cart.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

    List<Cart> findByUsuarioId(Integer usuarioId);

    void deleteByUsuarioId(Integer usuarioId);

}
