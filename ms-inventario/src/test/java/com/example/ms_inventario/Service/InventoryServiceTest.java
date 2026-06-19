package com.example.ms_inventario.Service;

import com.example.ms_inventario.Model.Inventory;
import com.example.ms_inventario.Repository.InventoryRepository;
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
public class InventoryServiceTest {


    @InjectMocks
    private InventoryService inventoryService;

    
    @Mock
    private InventoryRepository inventoryRepository;

    private Inventory inventarioFalso;

    @BeforeEach
    void setUp() {
        inventarioFalso = new Inventory();
        inventarioFalso.setNombreinv("Inventario de Tecnologia");
        inventarioFalso.setMesinv("Enero");
        inventarioFalso.setProductoIds(Arrays.asList(10, 11, 12)); 
    }


    @Test
    public void testObtenerInventarioPorId_Exitoso() {
        System.out.println("TEST INVENTARIO: EJECUTANDO CAMINO FELIZ");
        when(inventoryRepository.findById(1)).thenReturn(Optional.of(inventarioFalso));
        Inventory resultado = inventoryService.findById(1);

        assertNotNull(resultado);
        assertEquals("Inventario de Tecnologia", resultado.getNombreinv());
        assertEquals("Enero", resultado.getMesinv());
        assertEquals(3, resultado.getProductoIds().size()); 
        verify(inventoryRepository, times(1)).findById(1);
    }

    @Test
    public void testObtenerInventarioPorId_NoEncontrado() {
        System.out.println("TEST INVENTARIO NULL");
        when(inventoryRepository.findById(99)).thenReturn(Optional.empty());
        Inventory resultado = inventoryService.findById(99);
        assertNull(resultado, "Debería retornar null porque el inventario 99 no existe");
        verify(inventoryRepository, times(1)).findById(99);
    }
}