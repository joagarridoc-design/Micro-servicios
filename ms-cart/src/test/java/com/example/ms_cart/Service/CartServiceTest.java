package com.example.ms_cart.Service;

import com.example.ms_cart.Model.Cart;
import com.example.ms_cart.Repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

   
    @InjectMocks
    private CartService cartService;

    @Mock
    private CartRepository cartRepository;

    private Cart cartFalso;

    @BeforeEach
    void setUp() {
        cartFalso = new Cart();
        cartFalso.setUsuarioId(10); 
        cartFalso.setProductoId(55); 
        cartFalso.setCantidad(3);    
        cartFalso.setProductosIds(Arrays.asList(101, 102, 103)); 
    }
    @Test
    public void testObtenerCartPorId_Exitoso() {
        System.out.println("TEST CART: EJECUTANDO PROBABILIDAD ESPERADA");
        when(cartRepository.findById(1)).thenReturn(Optional.of(cartFalso));
        Cart resultado = cartService.findById(1);
        assertNotNull(resultado);
        assertEquals(10, resultado.getUsuarioId());
        assertEquals(55, resultado.getProductoId());
        assertEquals(3, resultado.getCantidad());
        assertEquals(3, resultado.getProductosIds().size()); 
        verify(cartRepository, times(1)).findById(1);
    }
    @Test
    public void testObtenerCartPorId_NoEncontrado() {
        System.out.println("TEST CART: EJECUTANDO POSIBILIDAD NULL");
        when(cartRepository.findById(99)).thenReturn(Optional.empty());
        Cart resultado = cartService.findById(99);
        assertNull(resultado, "Debería retornar NULL al no existir el carrito 99");
        verify(cartRepository, times(1)).findById(99);
    }
}