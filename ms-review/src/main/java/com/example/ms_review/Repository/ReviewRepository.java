package com.example.ms_review.Repository;

import com.example.ms_review.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{

    List<Review> findByProductoId(Integer productoId);

    List<Review> findByUsuarioId(Integer usuarioId);


}
