package com.example.ms_order.Service;

import org.junit.jupiter.api.Test;

import com.example.ms_order.Model.Orders;
import com.example.ms_order.Repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {


    @InjectMocks
    private OrderService orderService;

    
    @Mock
    private OrderRepository orderRepository;

    private Orders orderFalsa;

    @BeforeEach
    void setUp() {
        orderFalsa = new Orders();
        orderFalsa.setIdorden(1);
        orderFalsa.setEstado("pendiente");
        orderFalsa.setTotal("100.000$");
        orderFalsa.setUserIds(Arrays.asList(1, 2, 3));
    }
    @Test
    public void testSaveOrdenes() {
        System.out.println("TEST GUARDAR ORDENES");
        when(orderRepository.save(any(Orders.class))).thenReturn(orderFalsa);
        
        Orders resultado = orderService.saveOrdenes(orderFalsa);
        
        assertNotNull(resultado);
        assertEquals("pendiente", resultado.getEstado());
        assertEquals("100.000$", resultado.getTotal());
        verify(orderRepository, times(1)).save(orderFalsa);
    }


    @Test
    public void testObtenerOrdenPorId_Exitoso() {
        System.out.println("TEST ORDEN ENCONTRADA");
        when(orderRepository.findById(1)).thenReturn(Optional.of(orderFalsa));
        Orders resultado = orderService.findByIdorden(1);

        assertNotNull(resultado);
        assertEquals("pendiente", resultado.getEstado());
        assertEquals("100.000$", resultado.getTotal());
        assertEquals(3, resultado.getUserIds().size());
        verify(orderRepository, times(1)).findById(1);
    }
    @Test
    public void testGetOrdenes() {
        System.out.println("TEST OBTENER TODAS LAS ORDENES");
        when(orderRepository.findAll()).thenReturn(Arrays.asList(orderFalsa));
        
        List<Orders> resultado = orderService.getOrdenes();
        
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty(), "La lista de órdenes no debería estar vacía");
        assertEquals(1, resultado.size());
        assertEquals("pendiente", resultado.get(0).getEstado());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    public void testObtenerOrdenPorId_NoEncontrado() {
        System.out.println("TEST ORDEN NULL");
        when(orderRepository.findById(99)).thenReturn(Optional.empty());
        Orders resultado = orderService.findByIdorden(99);
        assertNull(resultado, "Debería retornar null porque la orden 99 no existe");
        verify(orderRepository, times(1)).findById(99);
    }
    @Test
    public void testGetOrderByEstado() {
        System.out.println("TEST OBTENER ORDEN POR ESTADO");
        String estadoBuscado = "pendiente";
        when(orderRepository.findByEstado(estadoBuscado)).thenReturn(Arrays.asList(orderFalsa));
        
        List<Orders> resultado = orderService.getOrderByEstado(estadoBuscado);
        
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(estadoBuscado, resultado.get(0).getEstado());
        verify(orderRepository, times(1)).findByEstado(estadoBuscado);
    }
    @Test
    public void testEliminarOrden() {
        System.out.println("TEST ELIMINAR ORDEN");
        Integer idEliminar = 1;
        orderService.eliminarOrden(idEliminar);
        verify(orderRepository, times(1)).deleteById(idEliminar);
    }
}