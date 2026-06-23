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
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
    public void testGetPaymentWithUser_Exitoso() {
        System.out.println("TEST PAYMENT: EJECUTANDO INTEGRACIÓN DE REPOSITORY Y FEIGN CLIENT");
        
  
        when(repository.findById(1)).thenReturn(Optional.of(paymentFalso));
        when(client.obtenerUsuarioPorId(10)).thenReturn(usuarioFalso);
        
  
        Map<String, Object> resultado = paymentService.getPaymentWithUser(1);
        
        assertNotNull(resultado, "El mapa resultante no debería ser nulo");
        assertEquals(1, resultado.get("idpago"));
        assertEquals("Tarjeta de Crédito", resultado.get("tipo"));
        assertEquals("250000.0", resultado.get("monto"));
        
        UserDTO userResult = (UserDTO) resultado.get("usuario");
        assertNotNull(userResult, "El objeto usuario dentro del mapa no debería ser nulo");
        assertEquals("Juan Pérez", userResult.getNombre());
        assertEquals(10, userResult.getId());
        
        verify(repository, times(1)).findById(1);
        verify(client, times(1)).obtenerUsuarioPorId(10);
    }
    @Test
    public void testGetPagos() {
        System.out.println("TEST PAYMENT EJECUTANDO OBTENER TODOS LOS PAGOS");
        
       
        List<Payment> listaEsperada = Arrays.asList(paymentFalso);
        when(repository.findAll()).thenReturn(listaEsperada);
        
      
        List<Payment> resultado = paymentService.getPagos();
        
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(repository, times(1)).findAll();
    }
    @Test
    public void testSavePagos() {
        System.out.println("TEST PAYMENT EJECUTANDO SAVE PAGOS");
        
  
        when(repository.save(any(Payment.class))).thenReturn(paymentFalso);
    
        Payment resultado = paymentService.savePagos(paymentFalso);
        
      
        assertNotNull(resultado);
        assertEquals("Tarjeta de Crédito", resultado.getTipo());
        verify(repository, times(1)).save(paymentFalso);
    }


    @Test
    public void testFindById_Exitoso() {
        System.out.println("EJECUTANDO BUSCAR PAGO POR ID (EXISTENTE)");
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
    public void testGetPaymentsByTipo() {
        System.out.println("TEST PAYMENT EJECUTANDO OBTENER POR TIPO");
        
        String tipoBuscado = "Tarjeta de Crédito";
        List<Payment> listaEsperada = Arrays.asList(paymentFalso);
        when(repository.findByTipo(tipoBuscado)).thenReturn(listaEsperada);
        
        List<Payment> resultado = paymentService.getPaymentsByTipo(tipoBuscado);
        
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(tipoBuscado, resultado.get(0).getTipo());
        verify(repository, times(1)).findByTipo(tipoBuscado);
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
    public void testEliminarPago() {
        System.out.println("TEST PAYMENT EJECUTANDO ELIMINAR PAGO");
        
        Integer idEliminar = 1;
        doNothing().when(repository).deleteById(idEliminar);
        paymentService.eliminarPago(idEliminar);
        
        verify(repository, times(1)).deleteById(idEliminar);
    }
}

    