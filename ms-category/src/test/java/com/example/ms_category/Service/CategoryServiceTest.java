package com.example.ms_category.Service;

import com.example.ms_category.Model.Category;
import com.example.ms_category.Repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

   
    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    private Category categoryFalsa;

    @BeforeEach
    void setUp() {
        categoryFalsa = new Category();
        categoryFalsa.setId(1);
        categoryFalsa.setName("Electrónica");
        categoryFalsa.setProductosIds(Arrays.asList(55, 56, 57));
    }
    @Test
    public void testObtenerCategoryPorId_Exitoso() {
        System.out.println("TEST CATEGORY: EJECUTANDO PROBABILIDAD ESPERADA");
        when(categoryRepository.findById(1)).thenReturn(Optional.of(categoryFalsa));
        Category resultado = categoryService.findById(1);
        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Electrónica", resultado.getName());
        assertEquals(3, resultado.getProductosIds().size());
        verify(categoryRepository, times(1)).findById(1);
    }
    @Test
    public void testObtenerCategoryPorId_NoEncontrado() {
        System.out.println("TEST CATEGORY: EJECUTANDO POSIBILIDAD NULL");
        when(categoryRepository.findById(99)).thenReturn(Optional.empty());
        Category resultado = categoryService.findById(99);
        assertNull(resultado, "Debería retornar NULL al no existir la categoría 99");
        verify(categoryRepository, times(1)).findById(99);
    }
}