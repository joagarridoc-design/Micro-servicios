package com.example.ms_review.Service;

import com.example.ms_review.Model.Review;
import com.example.ms_review.Repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {


    @InjectMocks
    private ReviewService reviewService;

    
    @Mock
    private ReviewRepository reviewRepository;

    private Review reviewFalso;

    @BeforeEach
    void setUp() {
        reviewFalso = new Review();
        reviewFalso.setUsuarioId(1);
        reviewFalso.setProductoId(1);
        reviewFalso.setComentario("Excelente producto, muy recomendable."); 
    }
    @Test
    public void testSaveReview() {
        System.out.println("TEST REVIEW EJECUTANDO SAVE REVIEW");
        when(reviewRepository.save(any(Review.class))).thenReturn(reviewFalso);
        Review resultado = reviewService.saveReview(reviewFalso);
        assertNotNull(resultado);
        assertEquals(1, resultado.getUsuarioId());
        assertEquals(1, resultado.getProductoId());
        assertEquals("Excelente producto, muy recomendable.", resultado.getComentario());
        verify(reviewRepository, times(1)).save(reviewFalso);
    }
    @Test
    public void testGetAllReviews() {
        System.out.println("TEST REVIEW: EJECUTANDO OBTENER TODAS LAS REVIEWS");
        List<Review> listaEsperada = Arrays.asList(reviewFalso);
        when(reviewRepository.findAll()).thenReturn(listaEsperada);
        List<Review> resultado = reviewService.getAllReviews();
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(reviewRepository, times(1)).findAll();
    }


    @Test
    public void testObtenerReviewPorId_Exitoso() {
        System.out.println("TEST REVIEW ENCONTRADO");
        when(reviewRepository.findById(1)).thenReturn(Optional.of(reviewFalso));
        Review resultado = reviewService.findById(1);
        assertNotNull(resultado);
        assertEquals("Excelente producto, muy recomendable.", resultado.getComentario());
        verify(reviewRepository, times(1)).findById(1);
    }
    @Test
    public void testGetReviewsByProducto() {
        System.out.println("TEST REVIEW EJECUTANDO OBTENER POR PRODUCTO ID");
        Integer productoIdBuscado = 1;
        List<Review> listaEsperada = Arrays.asList(reviewFalso);
        when(reviewRepository.findByProductoId(productoIdBuscado)).thenReturn(listaEsperada);
        List<Review> resultado = reviewService.getReviewsByProducto(productoIdBuscado);
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(productoIdBuscado, resultado.get(0).getProductoId());
        verify(reviewRepository, times(1)).findByProductoId(productoIdBuscado);
    }
    @Test
    public void testGetReviewsByUsuario() {
        System.out.println("TEST REVIEW EJECUTANDO OBTENER POR USUARIO ID");
        Integer usuarioIdBuscado = 1;
        List<Review> listaEsperada = Arrays.asList(reviewFalso);
        when(reviewRepository.findByUsuarioId(usuarioIdBuscado)).thenReturn(listaEsperada);
        List<Review> resultado = reviewService.getReviewsByUsuario(usuarioIdBuscado);
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(usuarioIdBuscado, resultado.get(0).getUsuarioId());
        verify(reviewRepository, times(1)).findByUsuarioId(usuarioIdBuscado);
    }
    @Test
    public void testDeleteReview() {
        System.out.println("TEST REVIEW EJECUTANDO ELIMINAR REVIEW");
        Integer idEliminar = 1;
        doNothing().when(reviewRepository).deleteById(idEliminar);
        reviewService.deleteReview(idEliminar);
        verify(reviewRepository, times(1)).deleteById(idEliminar);
    }

    @Test
    public void testObtenerReviewPorId_NoEncontrado() {
        System.out.println("TEST REVIEW NULL");
        when(reviewRepository.findById(99)).thenReturn(Optional.empty());
        Review resultado = reviewService.findById(99);
        assertNull(resultado, "Debería retornar null porque el review 99 no existe");
        verify(reviewRepository, times(1)).findById(99);
    }
}