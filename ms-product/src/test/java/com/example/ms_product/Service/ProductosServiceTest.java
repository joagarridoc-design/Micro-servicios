package com.example.ms_product.Service;

import com.example.ms_product.Model.Productos;
import com.example.ms_product.Repository.ProductosRepository;
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
public class ProductosServiceTest {
@InjectMocks
private ProductosService productosService;

@Mock
private ProductosRepository productosRepository;

private Productos productoFalso;

@BeforeEach
    public void setUp() {
    productoFalso = new Productos();
    productoFalso.setNombre("Poleron rojo");
    productoFalso.setCategoriasIds(Arrays.asList(1, 2));
    }

    @Test
    public void testObtenerProductoPorId() {
    System.out.println("EJECUTANDO LA PRUEBA CON MOCKITO");
    when(productosRepository.findById(1)).thenReturn(Optional.of(productoFalso));
    System.out.println("EJECUTANDO LA PRUEBA CON MOCKITO 2");
    Productos resultado = productosService.findById(1);
    assertNotNull(resultado);
    assertEquals("Poleron rojo", resultado.getNombre());
    verify(productosRepository, times(1)).findById(1);
    }

    @Test
    public void testObtenerProductoPorId_NoEncontrado() {
        System.out.println(" EJECUTANDO LA PRUEBA (NULL)");

        when(productosRepository.findById(99)).thenReturn(Optional.empty());
        Productos resultado = productosService.findById(99);
        assertNull(resultado, " el ID 99 no existe, por tanto es NULL");
        verify(productosRepository, times(1)).findById(99);
    }
    @Test
    public void testSaveProductos() {
        System.out.println("TEST PRODUCTO EJECUTANDO GUARDAR PRODUCTOS");
        when(productosRepository.save(any(Productos.class))).thenReturn(productoFalso);
        Productos resultado = productosService.saveProductos(productoFalso);
        assertNotNull(resultado);
        assertEquals("Poleron rojo", resultado.getNombre());
        verify(productosRepository, times(1)).save(productoFalso);
    }
    @Test
    public void testGetProductos() {
        System.out.println("TEST PRODUCTO: EJECUTANDO OBTENER TODOS LOS PRODUCTOS");
        List<Productos> listaEsperada = Arrays.asList(productoFalso);
        when(productosRepository.findAll()).thenReturn(listaEsperada);
        List<Productos> resultado = productosService.getProductos();
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(productosRepository, times(1)).findAll();
    }
    @Test
    public void testEliminarProducto() {
        System.out.println("TEST PRODUCTO: EJECUTANDO ELIMINAR PRODUCTO");
        Integer idEliminar = 1;
        doNothing().when(productosRepository).deleteById(idEliminar);
        productosService.eliminarProducto(idEliminar);
        verify(productosRepository, times(1)).deleteById(idEliminar);
    }
}