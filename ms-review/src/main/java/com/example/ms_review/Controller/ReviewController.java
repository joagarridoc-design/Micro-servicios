package com.example.ms_review.Controller;


import com.example.ms_review.Model.Review;
import com.example.ms_review.Service.ReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@CrossOrigin(origins = "http://localhost:8080")
@Tag(name ="reviews", description= "operacion relacionadas con reviews")
public class ReviewController {
    @Autowired
    private ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }


    @PostMapping
    @Operation(summary = "Guardar reseña",
        description = "Guarda la reseña "
    )
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Reseña creada exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Review.class))),
    @ApiResponse(responseCode = "404", description = "Reseña no se ha podido crear")
})
    public ResponseEntity<Review> guardarReview(@Valid @RequestBody Review review) {
    Review guardada = service.saveReview(review);
    return new ResponseEntity<>(guardada, HttpStatus.CREATED);
    }
    @GetMapping
    @Operation(summary = "Listar",
        description = "Lista las reseñas "
    )
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Reseñas listadas exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Review.class))),
    @ApiResponse(responseCode = "404", description = "Reseñas no se han podido listar")
})
    public List<Review> listarTodas() {
        return service.getAllReviews();
    }

    @GetMapping("/producto/{productoId}")
    @Operation(summary = "Buscar por ID de producto",
        description = "Busca el producto por su ID en la reseña"
    )
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Producto encontrado exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Review.class))),
    @ApiResponse(responseCode = "404", description = "no se ha Podido encontrar el producto")
})
    public List<Review> buscarPorProducto(@PathVariable ("productoId")Integer productoId) {
        return service.getReviewsByProducto(productoId);
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Buscar por ID de usuario",
        description = "Busca el usuario por su ID en la reseña"
    )
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "usuario encontrado exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Review.class))),
    @ApiResponse(responseCode = "404", description = "no se ha Podido encontrar el usuario")
})
    public List<Review> buscarPorUsuario(@PathVariable ("usuarioId")Integer usuarioId) {
        return service.getReviewsByUsuario(usuarioId);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar reseña por ID",
        description = "Elimina la reseña por el ID"
    )
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Reseña eliminada exitosamente",
       content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Review.class))),
    @ApiResponse(responseCode = "404", description = "no se ha Podido encontrar la reseña")
})
    public void eliminarReview(@PathVariable ("id")Integer id) {
        service.deleteReview(id);
    }
    @PutMapping("/{id}")
@Operation(summary = "modifica review por ID", description = "modifica las reviews por su ID")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Reseña actualizada exitosamente",
       content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Review.class))),
    @ApiResponse(responseCode = "404", description = "no se ha Podido actualizar la reseña")
})
public ResponseEntity<Review> actualizar(@PathVariable Integer id, @RequestBody Review reviewDetalles) {
    try {
        Review review = service.findById(id);
        review.setId(id); 
        review.setUsuarioId(reviewDetalles.getUsuarioId());
        review.setProductoId(reviewDetalles.getProductoId());
        review.setComentario(reviewDetalles.getComentario());
        review.setPuntuacion(reviewDetalles.getPuntuacion());
        service.save(review);
        return ResponseEntity.ok(review); 
        
    } catch (Exception e) {
        return ResponseEntity.notFound().build();
    }
}

    
   
}

