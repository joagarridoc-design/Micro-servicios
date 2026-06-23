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
import java.util.List;
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
    public void testSave() {
        System.out.println("TEST GUARDAR USUARIO");
        when(userRepository.save(any(User.class))).thenReturn(userFalso);
        
        User resultado = userService.save(userFalso);
        
        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("juan", resultado.getNombre());
        verify(userRepository, times(1)).save(userFalso);
    }
    @Test
    public void testGetUsersByNombre() {
        System.out.println("TEST OBTENER USUARIOS POR NOMBRE");
        String nombreBuscado = "juan";
        when(userRepository.findByNombre(nombreBuscado)).thenReturn(Arrays.asList(userFalso));
        List<User> resultado = userService.getUsersByNombre(nombreBuscado);
        
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(nombreBuscado, resultado.get(0).getNombre());
        verify(userRepository, times(1)).findByNombre(nombreBuscado);
    }
    
    @Test
    public void testEliminarUsuario() {
        System.out.println("TEST ELIMINAR USUARIO");
        Integer idEliminar = 1;
        
        userService.eliminarUsuario(idEliminar);
        
        verify(userRepository, times(1)).deleteById(idEliminar);
    }
    @Test
    public void testGetUsers() {
        System.out.println("TEST OBTENER TODOS LOS USUARIOS");
        when(userRepository.findAll()).thenReturn(Arrays.asList(userFalso));
        
        List<User> resultado = userService.getUsers();
        
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals("juan", resultado.get(0).getNombre());
        verify(userRepository, times(1)).findAll();
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