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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
    @Test
    public void testGetInventarios() {
        System.out.println("TEST INVENTARIO: EJECUTANDO OBTENER TODOS LOS INVENTARIOS");
        List<Inventory> listaEsperada = Arrays.asList(inventarioFalso);
        when(inventoryRepository.findAll()).thenReturn(listaEsperada);
        List<Inventory> resultado = inventoryService.getInventarios();
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(inventoryRepository, times(1)).findAll();
    }
    @Test
    public void testGetInventariosByMes() {
        System.out.println("TEST INVENTARIO EJECUTANDO OBTENER POR MES");
        String mesBuscado = "Enero";
        List<Inventory> listaEsperada = Arrays.asList(inventarioFalso);
        when(inventoryRepository.findByMesinv(mesBuscado)).thenReturn(listaEsperada);
        List<Inventory> resultado = inventoryService.getInventariosByMes(mesBuscado);
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(mesBuscado, resultado.get(0).getMesinv());
        verify(inventoryRepository, times(1)).findByMesinv(mesBuscado);
    }
    @Test
    public void testGetInventariosByNombre() {
        System.out.println("TEST INVENTARIO EJECUTANDO OBTENER POR NOMBRE");
        
      
        String nombreBuscado = "Inventario de Tecnologia";
        List<Inventory> listaEsperada = Arrays.asList(inventarioFalso);
        when(inventoryRepository.findByNombreinv(nombreBuscado)).thenReturn(listaEsperada);
        List<Inventory> resultado = inventoryService.getInventariosByNombre(nombreBuscado);
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(nombreBuscado, resultado.get(0).getNombreinv());
        verify(inventoryRepository, times(1)).findByNombreinv(nombreBuscado);
    }

    @Test
    public void testSaveInventarios() {
        System.out.println("TEST INVENTARIO EJECUTANDO GUARDAR INVENTARIOS");
        when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventarioFalso);
        Inventory resultado = inventoryService.saveInventarios(inventarioFalso);
        assertNotNull(resultado);
        assertEquals("Inventario de Tecnologia", resultado.getNombreinv());
        verify(inventoryRepository, times(1)).save(inventarioFalso);
    }

    @Test
    public void testEliminarInventario() {
        System.out.println("TEST INVENTARIO: EJECUTANDO ELIMINAR INVENTARIO");
        
        Integer idEliminar = 1;
        doNothing().when(inventoryRepository).deleteById(idEliminar);
        inventoryService.eliminarInventario(idEliminar);
        verify(inventoryRepository, times(1)).deleteById(idEliminar);
    }

}