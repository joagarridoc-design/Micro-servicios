package com.example.ms_review.Service;

import com.example.ms_review.Model.Review;
import com.example.ms_review.Repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    public void testObtenerReviewPorId_Exitoso() {
        System.out.println("TEST REVIEW ENCONTRADO");
        when(reviewRepository.findById(1)).thenReturn(Optional.of(reviewFalso));
        Review resultado = reviewService.findById(1);

        assertNotNull(resultado);
        assertEquals("Excelente producto, muy recomendable.", resultado.getComentario());
        verify(reviewRepository, times(1)).findById(1);
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