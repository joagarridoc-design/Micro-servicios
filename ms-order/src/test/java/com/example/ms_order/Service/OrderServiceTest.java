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

import static org.junit.jupiter.api.Assertions.*;
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
    public void testObtenerOrdenPorId_NoEncontrado() {
        System.out.println("TEST ORDEN NULL");
        when(orderRepository.findById(99)).thenReturn(Optional.empty());
        Orders resultado = orderService.findByIdorden(99);
        assertNull(resultado, "Debería retornar null porque la orden 99 no existe");
        verify(orderRepository, times(1)).findById(99);
    }
}