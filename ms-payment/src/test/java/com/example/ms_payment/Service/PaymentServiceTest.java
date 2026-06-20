package com.example.ms_payment.Service;


import com.example.ms_payment.Client.UserFeignClient;
import com.example.ms_payment.Model.Payment;
import com.example.ms_payment.Model.DTO.UserDTO;
import com.example.ms_payment.Repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.Map;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
@InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentRepository repository;

    @Mock
    private UserFeignClient client;

    private Payment paymentFalso;
    private UserDTO usuarioFalso;

    @BeforeEach
    public void setUp() {
        paymentFalso = new Payment();
        paymentFalso.setIdpago(1);
        paymentFalso.setTipo("Tarjeta de Crédito");
        paymentFalso.setMonto("250000.0");
        paymentFalso.setUserIds(Arrays.asList(10));

        
        usuarioFalso = new UserDTO();
        usuarioFalso.setId(10);
        usuarioFalso.setNombre("Juan Pérez");
    }


    @Test
    public void testFindById_Exitoso() {
    System.out.println("EJECUTANDO: BUSCAR PAGO POR ID (EXISTENTE)");
    
    
    Payment paymentFalso = new Payment();
    paymentFalso.setIdpago(1);
    paymentFalso.setTipo("Tarjeta de Crédito");
    paymentFalso.setMonto("25000.0"); 

    when(repository.findById(1)).thenReturn(Optional.of(paymentFalso));
    Payment resultado = paymentService.findById(1);

    assertNotNull(resultado, "El pago devuelto no debería ser nulo");
    assertEquals("Tarjeta de Crédito", resultado.getTipo());
    assertEquals("25000.0", resultado.getMonto()); 
    verify(repository, times(1)).findById(1);
}

    @Test
    public void testFindById_NoEncontrado() {
        System.out.println("EJECUTANDO: BUSCAR PAGO POR ID (NULL)");

        when(repository.findById(99)).thenReturn(Optional.empty());
        
        Payment resultado = paymentService.findById(99);

        assertNull(resultado, "El ID 99 no existe, por lo tanto el servicio retorna null");
        verify(repository, times(1)).findById(99);
    }

    @Test
public void testGetPaymentWithUser_Exitoso() {
    System.out.println("EJECUTANDO: INTEGRACIÓN DE REPOSITORY Y FEIGN CLIENT");

    
    Payment paymentFalso = new Payment();
    paymentFalso.setIdpago(1);
    paymentFalso.setTipo("Tarjeta de Crédito");
    paymentFalso.setMonto("25000.0"); 
    paymentFalso.setUserIds(Arrays.asList(10)); 

    UserDTO usuarioFalso = new UserDTO();
    usuarioFalso.setId(10);
    usuarioFalso.setNombre("Juan Pérez");

    
    when(repository.findById(1)).thenReturn(Optional.of(paymentFalso));
    when(client.obtenerUsuarioPorId(10)).thenReturn(usuarioFalso);

    Map<String, Object> resultado = paymentService.getPaymentWithUser(1);

    
    assertNotNull(resultado, "El mapa resultante no debería ser nulo");
    assertEquals(1, resultado.get("idpago"));
    assertEquals("Tarjeta de Crédito", resultado.get("tipo"));
    assertEquals("25000.0", resultado.get("monto")); 

    
    UserDTO userResult = (UserDTO) resultado.get("usuario");
    assertNotNull(userResult, "El objeto usuario dentro del mapa no debería ser nulo");
    assertEquals("Juan Pérez", userResult.getNombre());

    
    verify(repository, times(1)).findById(1);
    verify(client, times(1)).obtenerUsuarioPorId(10);
}
}
