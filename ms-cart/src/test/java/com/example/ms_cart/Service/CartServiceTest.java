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
import static org.mockito.ArgumentMatchers.any;
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
    public void testSaveCart() {
        System.out.println("TEST CART: EJECUTANDO SAVE CART");
        
      
        when(cartRepository.save(any(Cart.class))).thenReturn(cartFalso);
        
    
        Cart resultado = cartService.saveCart(cartFalso);
        assertNotNull(resultado);
        assertEquals(10, resultado.getUsuarioId());
        assertEquals(55, resultado.getProductoId());
        verify(cartRepository, times(1)).save(cartFalso);
    }
    @Test
    public void testGetAllCarts() {
        System.out.println("TEST CART EJECUTANDO OBTENER TODOS LOS CARRITOS");
        List<Cart> listaEsperada = Arrays.asList(cartFalso);
        when(cartRepository.findAll()).thenReturn(listaEsperada);
        
        
        List<Cart> resultado = cartService.getAllCarts();
        
     
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(cartRepository, times(1)).findAll();
    }
    @Test
    public void testGetCartByUsuario() {
        System.out.println("TEST CART EJECUTANDO OBTENER POR USUARIO ID");
       
        Integer usuarioIdBuscado = 10;
        List<Cart> listaEsperada = Arrays.asList(cartFalso);
        when(cartRepository.findByUsuarioId(usuarioIdBuscado)).thenReturn(listaEsperada);
        
    
        List<Cart> resultado = cartService.getCartByUsuario(usuarioIdBuscado);
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(usuarioIdBuscado, resultado.get(0).getUsuarioId());
        verify(cartRepository, times(1)).findByUsuarioId(usuarioIdBuscado);
    }
    @Test
    public void testDeleteItem() {
        System.out.println("TEST CART EJECUTANDO ELIMINAR ITEM POR ID");
        Integer idEliminar = 1;
        doNothing().when(cartRepository).deleteById(idEliminar);
        
       
        cartService.deleteItem(idEliminar);
        verify(cartRepository, times(1)).deleteById(idEliminar);
    }
    @Test
    public void testClearUsuarioCart() {
        System.out.println("TEST CART EJECUTANDO LIMPIAR CARRITO DEL USUARIO");
        Integer usuarioIdLimpiar = 10;
        doNothing().when(cartRepository).deleteByUsuarioId(usuarioIdLimpiar);
        
      
        cartService.clearUsuarioCart(usuarioIdLimpiar);
        verify(cartRepository, times(1)).deleteByUsuarioId(usuarioIdLimpiar);
    }
    @Test
    public void testSave() {
        System.out.println("TEST CART EJECUTANDO SAVE (MÉTODO DUPLICADO)");
        when(cartRepository.save(any(Cart.class))).thenReturn(cartFalso);
        Cart resultado = cartService.save(cartFalso);
        
        
        assertNotNull(resultado);
        assertEquals(3, resultado.getCantidad());
        verify(cartRepository, times(1)).save(cartFalso);
    }
}