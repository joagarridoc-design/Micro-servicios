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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
    public void testSaveSucursales() {
        System.out.println("TEST GUARDAR SUCURSAL");
        when(sucursalRepository.save(any(Sucursal.class))).thenReturn(sucursalFalsa);
        
        Sucursal resultado = sucursalService.saveSucursales(sucursalFalsa);
        
        assertNotNull(resultado);
        assertEquals("Paine", resultado.getRegion());
        assertEquals("Calama", resultado.getComuna());
        verify(sucursalRepository, times(1)).save(sucursalFalsa);
    }
    @Test
    public void testGetSucursales() {
        System.out.println("TEST OBTENER TODAS LAS SUCURSALES");
        when(sucursalRepository.findAll()).thenReturn(Arrays.asList(sucursalFalsa));
        
        List<Sucursal> resultado = sucursalService.getSucursales();
        
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty(), "La lista de sucursales no debería estar vacía");
        assertEquals(1, resultado.size());
        assertEquals("Paine", resultado.get(0).getRegion());
        verify(sucursalRepository, times(1)).findAll();
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
    public void testGetSucursalByRegion() {
        System.out.println("TEST OBTENER SUCURSALES POR REGION");
        String regionBuscada = "Paine";
        when(sucursalRepository.findByRegion(regionBuscada)).thenReturn(Arrays.asList(sucursalFalsa));
        
        List<Sucursal> resultado = sucursalService.getSucursalByRegion(regionBuscada);
        
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(regionBuscada, resultado.get(0).getRegion());
        verify(sucursalRepository, times(1)).findByRegion(regionBuscada);
    }
    @Test
    public void testGetSucursalesByNombre() {
        System.out.println("TEST OBTENER SUCURSALES POR COMUNA");
        String comunaBuscada = "Calama";
        when(sucursalRepository.findByComuna(comunaBuscada)).thenReturn(Arrays.asList(sucursalFalsa));
        
        List<Sucursal> resultado = sucursalService.getSucursalesByNombre(comunaBuscada);
        
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(comunaBuscada, resultado.get(0).getComuna());
        verify(sucursalRepository, times(1)).findByComuna(comunaBuscada);
    }

    @Test
    public void testObtenerSucursalPorId_NoEncontrado() {
        System.out.println("TEST SUCURSAL NULL");
        when(sucursalRepository.findById(99)).thenReturn(Optional.empty());
        Sucursal resultado = sucursalService.findById(99);
        assertNull(resultado, "Debería retornar null porque la sucursal 99 no existe");
        verify(sucursalRepository, times(1)).findById(99);
    }
    @Test
    public void testEliminarSucursal() {
        System.out.println("TEST ELIMINAR SUCURSAL");
        Integer idEliminar = 1;
        
        sucursalService.eliminarSucursal(idEliminar);
        verify(sucursalRepository, times(1)).deleteById(idEliminar);
    }
}