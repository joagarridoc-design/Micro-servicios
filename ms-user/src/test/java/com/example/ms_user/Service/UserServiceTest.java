package com.example.ms_user.Service;

import com.example.ms_user.Model.User;
import com.example.ms_user.Repository.UserRepository;
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
public class UserServiceTest {


    @InjectMocks
    private UserService userService;

    
    @Mock
    private UserRepository userRepository;

    private User userFalso;

    @BeforeEach
    void setUp() {
        userFalso = new User();
        userFalso.setId(1);
        userFalso.setNombre("juan");
        userFalso.setProductosIds(Arrays.asList(1,2,3));
    }


    @Test
    public void testObtenerInventarioPorId_Exitoso() {
        System.out.println("TEST INVENTARIO ENCONTRADO");
        when(userRepository.findById(1)).thenReturn(Optional.of(userFalso));
        User resultado = userService.findById(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("juan", resultado.getNombre());
        assertEquals(Arrays.asList(1, 2, 3), resultado.getProductosIds());
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    public void testObtenerInventarioPorId_NoEncontrado() {
        System.out.println("TEST INVENTARIO NULL");
        when(userRepository.findById(99)).thenReturn(Optional.empty());
        User resultado = userService.findById(99);
        assertNull(resultado, "Debería retornar null porque el usuario 99 no existe");
        verify(userRepository, times(1)).findById(99);
    }
}