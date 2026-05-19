package com.example.ms_review.Controller;


import com.example.ms_review.Model.Review;
import com.example.ms_review.Service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @Autowired
    private ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<Review> guardarReview(@Valid @RequestBody Review review) {
    Review guardada = service.saveReview(review);
    return new ResponseEntity<>(guardada, HttpStatus.CREATED);
    }
    @GetMapping
    public List<Review> listarTodas() {
        return service.getAllReviews();
    }

    @GetMapping("/producto/{productoId}")
    public List<Review> buscarPorProducto(@PathVariable Integer productoId) {
        return service.getReviewsByProducto(productoId);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Review> buscarPorUsuario(@PathVariable Integer usuarioId) {
        return service.getReviewsByUsuario(usuarioId);
    }

    @DeleteMapping("/{id}")
    public void eliminarReview(@PathVariable Integer id) {
        service.deleteReview(id);
    }
    

}
