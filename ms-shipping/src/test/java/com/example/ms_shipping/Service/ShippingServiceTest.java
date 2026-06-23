package com.example.ms_shipping.Service;

import com.example.ms_shipping.Model.Shipping;
import com.example.ms_shipping.Repository.ShippingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
    public void testUpdate_NoEncontrado_LanzaExcepcion() {
        System.out.println("TEST SHIPPING EJECUTANDO UPDATE FALLIDO (NO ENCONTRADO)");
        Integer idErroneo = 99;
        when(shippingRepository.findById(idErroneo)).thenReturn(Optional.empty());
        Exception excepcion = assertThrows(RuntimeException.class, () -> {
            shippingService.update(idErroneo, shippingFalso);
        });
        assertEquals("Envío no encontrado", excepcion.getMessage());
        verify(shippingRepository, times(1)).findById(idErroneo);
        verify(shippingRepository, never()).save(any(Shipping.class)); 
    }
    @Test
    public void testSaveEnvios() {
        System.out.println("TEST SHIPPING EJECUTANDO SAVE ENVIOS");
        when(shippingRepository.save(any(Shipping.class))).thenReturn(shippingFalso);
        Shipping resultado = shippingService.saveEnvios(shippingFalso);
        assertNotNull(resultado);
        assertEquals("Entregado", resultado.getEstado());
        verify(shippingRepository, times(1)).save(shippingFalso);
    }
    @Test
    public void testGetEnvios() {
        System.out.println("TEST SHIPPING: EJECUTANDO OBTENER TODOS LOS ENVIOS");
        List<Shipping> listaEsperada = Arrays.asList(shippingFalso);
        when(shippingRepository.findAll()).thenReturn(listaEsperada);
        List<Shipping> resultado = shippingService.getEnvios();
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(shippingRepository, times(1)).findAll();
    }
    @Test
    public void testUpdate_Exitoso() {
        System.out.println("TEST SHIPPING EJECUTANDO UPDATE EXITOSO");
        Integer id = 1;
        when(shippingRepository.findById(id)).thenReturn(Optional.of(shippingFalso));
        when(shippingRepository.save(any(Shipping.class))).thenReturn(shippingFalso);
        Shipping resultado = shippingService.update(id, shippingFalso);
        assertNotNull(resultado);
        verify(shippingRepository, times(1)).findById(id);
        verify(shippingRepository, times(1)).save(shippingFalso);
    }

    @Test
    public void testObtenerShippingPorId_NoEncontrado() {
        System.out.println("TEST SHIPPING NULL");
        when(shippingRepository.findById(99)).thenReturn(Optional.empty());
        Shipping resultado = shippingService.findById(99);
        assertNull(resultado, "Debería retornar null porque el envío 99 no existe");
        verify(shippingRepository, times(1)).findById(99);
    }
    @Test
    public void testGetShippingByMesinicio() {
        System.out.println("TEST SHIPPING EJECUTANDO OBTENER POR MES INICIO");
        String mesInicio = "Enero";
        List<Shipping> listaEsperada = Arrays.asList(shippingFalso);
        when(shippingRepository.findByMesinicio(mesInicio)).thenReturn(listaEsperada);
        List<Shipping> resultado = shippingService.getShippingByMesinicio(mesInicio);
        assertNotNull(resultado);
        assertEquals(mesInicio, resultado.get(0).getMesinicio());
        verify(shippingRepository, times(1)).findByMesinicio(mesInicio);
    }
    @Test
    public void testGetShippingByMesllegada() {
        System.out.println("TEST SHIPPING EJECUTANDO OBTENER POR MES LLEGADA");
        String mesLlegada = "Febrero";
        List<Shipping> listaEsperada = Arrays.asList(shippingFalso);
        when(shippingRepository.findByMesllegada(mesLlegada)).thenReturn(listaEsperada);
        List<Shipping> resultado = shippingService.getShippingByMesllegada(mesLlegada);
        assertNotNull(resultado);
        assertEquals(mesLlegada, resultado.get(0).getMesllegada());
        verify(shippingRepository, times(1)).findByMesllegada(mesLlegada);
    }
    @Test
    public void testEliminarEnvio() {
        System.out.println("TEST SHIPPING EJECUTANDO ELIMINAR ENVIO");
        Integer idEliminar = 1;
        doNothing().when(shippingRepository).deleteById(idEliminar);
        shippingService.eliminarEnvio(idEliminar);
        verify(shippingRepository, times(1)).deleteById(idEliminar);
    }
}