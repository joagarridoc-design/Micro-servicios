package com.example.ms_sucursal.Service;

import com.example.ms_sucursal.Model.Sucursal;
import com.example.ms_sucursal.Repository.SucursalRepository;
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
public class SucursalServiceTest {


    @InjectMocks
    private SucursalService sucursalService;

    
    @Mock
    private SucursalRepository sucursalRepository;

    private Sucursal sucursalFalsa;

    @BeforeEach
    void setUp() {
        sucursalFalsa = new Sucursal();
        sucursalFalsa.setRegion("Paine");
        sucursalFalsa.setComuna("Calama");
        sucursalFalsa.setInventoryIds(Arrays.asList(10, 11, 12)); 
    }


    @Test
    public void testObtenerSucursalPorId_Exitoso() {
        System.out.println("TEST SUCURSAL POR ID EXITOSO");
        when(sucursalRepository.findById(1)).thenReturn(Optional.of(sucursalFalsa));
        Sucursal resultado = sucursalService.findById(1);

        assertNotNull(resultado);
        assertEquals("Paine", resultado.getRegion());
        assertEquals("Calama", resultado.getComuna());
        assertEquals(3, resultado.getInventoryIds().size());
        verify(sucursalRepository, times(1)).findById(1);
    }

    @Test
    public void testObtenerSucursalPorId_NoEncontrado() {
        System.out.println("TEST SUCURSAL NULL");
        when(sucursalRepository.findById(99)).thenReturn(Optional.empty());
        Sucursal resultado = sucursalService.findById(99);
        assertNull(resultado, "Debería retornar null porque la sucursal 99 no existe");
        verify(sucursalRepository, times(1)).findById(99);
    }
}