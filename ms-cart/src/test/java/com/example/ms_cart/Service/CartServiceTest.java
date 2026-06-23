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
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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

    @Test
    public void testSaveCart_Exitoso() {
        System.out.println("TEST CART: EJECUTANDO saveCart");
        when(cartRepository.save(any(Cart.class))).thenReturn(cartFalso);
        
        Cart resultado = cartService.saveCart(cartFalso);
        
        assertNotNull(resultado);
        assertEquals(55, resultado.getProductoId());
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    
    @Test
    public void testGetAllCarts_Exitoso() {
        System.out.println("TEST CART: EJECUTANDO getAllCarts");
        List<Cart> listaFalsa = Arrays.asList(cartFalso, new Cart());
        when(cartRepository.findAll()).thenReturn(listaFalsa);
        
        List<Cart> resultado = cartService.getAllCarts();
        
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(cartRepository, times(1)).findAll();
    }

    
    @Test
    public void testGetCartByUsuario_Exitoso() {
        System.out.println("TEST CART: EJECUTANDO getCartByUsuario");
        List<Cart> listaFalsa = Arrays.asList(cartFalso);
        when(cartRepository.findByUsuarioId(10)).thenReturn(listaFalsa);
        
        List<Cart> resultado = cartService.getCartByUsuario(10);
        
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(cartRepository, times(1)).findByUsuarioId(10);
    }

    
    @Test
    public void testDeleteItem_Exitoso() {
        System.out.println("TEST CART: EJECUTANDO deleteItem (VOID)");
        doNothing().when(cartRepository).deleteById(1);
        
        cartService.deleteItem(1);
        
        verify(cartRepository, times(1)).deleteById(1);
    }

    
    @Test
    public void testClearUsuarioCart_Exitoso() {
        System.out.println("TEST CART: EJECUTANDO clearUsuarioCart (VOID)");
        doNothing().when(cartRepository).deleteByUsuarioId(10);
        
        cartService.clearUsuarioCart(10);
        
        verify(cartRepository, times(1)).deleteByUsuarioId(10);
    }

    
    @Test
    public void testSave_Exitoso() {
        System.out.println("TEST CART: EJECUTANDO método save genérico");
        when(cartRepository.save(any(Cart.class))).thenReturn(cartFalso);
        
        Cart resultado = cartService.save(cartFalso);
        
        assertNotNull(resultado);
        assertEquals(3, resultado.getCantidad());
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    
}