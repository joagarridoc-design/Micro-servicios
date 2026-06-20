package com.example.ms_shipping.Service;

import com.example.ms_shipping.Model.Shipping;
import com.example.ms_shipping.Repository.ShippingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShippingServiceTest {


    @InjectMocks
    private ShippingService shippingService;

    
    @Mock
    private ShippingRepository shippingRepository;

    private Shipping shippingFalso;

    @BeforeEach
    void setUp() {
        shippingFalso = new Shipping();
        shippingFalso.setIdship(1);
        shippingFalso.setEstado("Entregado");
        shippingFalso.setMesinicio("Enero");
        shippingFalso.setMesllegada("Febrero");
        shippingFalso.setOrdersIds(Arrays.asList(10)); 
    }


    @Test
    public void testObtenerShippingPorId_Exitoso() {
        System.out.println("TEST SHIPPING ENCONTRADO");
        when(shippingRepository.findById(1)).thenReturn(Optional.of(shippingFalso));
        Shipping resultado = shippingService.findById(1);

        assertNotNull(resultado);
        assertEquals("Entregado", resultado.getEstado());
        assertEquals("Enero", resultado.getMesinicio());
        assertEquals("Febrero", resultado.getMesllegada());
        assertEquals(1, resultado.getOrdersIds().size());
        verify(shippingRepository, times(1)).findById(1);
    }

    @Test
    public void testObtenerShippingPorId_NoEncontrado() {
        System.out.println("TEST SHIPPING NULL");
        when(shippingRepository.findById(99)).thenReturn(Optional.empty());
        Shipping resultado = shippingService.findById(99);
        assertNull(resultado, "Debería retornar null porque el envío 99 no existe");
        verify(shippingRepository, times(1)).findById(99);
    }
}