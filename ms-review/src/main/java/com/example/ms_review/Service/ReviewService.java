package com.example.ms_review.Service;

import com.example.ms_review.Model.Review;
import com.example.ms_review.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service

public class ReviewService {

    @Autowired
    private ReviewRepository repository;
    private static final Logger log = LoggerFactory.getLogger(ReviewService.class);

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public Review findById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public Review saveReview(Review review) {
        return repository.save(review);
    }

    public List<Review> getAllReviews() {
        return repository.findAll();
    }

    public List<Review> getReviewsByProducto(Integer productoId) {
        return repository.findByProductoId(productoId);
    }

    public List<Review> getReviewsByUsuario(Integer usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public void deleteReview(Integer id) {
        repository.deleteById(id);
    }

}
