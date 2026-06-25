package com.example.ms_category.Controller;

import com.example.ms_category.Model.Category;
import com.example.ms_category.Service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@CrossOrigin(origins = "http://localhost:8080")
@Tag(name = "Productos", description ="Operaciones relacionadas con las Categorias de productos")
public class CategoryController {

    @Autowired
    private CategoryService service;

    public CategoryController(CategoryService category) {
        this.service = category;
    }

    @PostMapping
    @Operation(summary = "Guardar",
        description = "Guarda categorías"
    )
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Categoria guardada exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Category.class))),
    @ApiResponse(responseCode = "404", description = "categoria no se ha podido guardar")
})
    public Category guardarCategory(@RequestBody Category category) {
        return service.saveCategory(category);
    }

    @GetMapping
    @Operation(summary = "Obtener",
        description = "Obtiene todas las categorias"
    )
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Categorias mostradas exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Category.class))),
    @ApiResponse(responseCode = "404", description = "No hay Categorías encontradas")
})
    public List<Category> listarTodas() {
        return service.getAllCategory();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar",
        description = "Elimina Categorías por ID"
    )
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Categoria eliminada exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Category.class))),
    @ApiResponse(responseCode = "404", description = "No hay Categorías encontradas")
})
    public void eliminarCategory(@PathVariable Integer id) {
        service.deleteCategory(id);
    }
    @PutMapping("/{id}")
    @Operation(summary = "modifica categorías por ID", description = "modifica las categorías por su ID")
    public ResponseEntity<Category> actualizar(@PathVariable Integer id, @RequestBody Category categoria) {
     try {
        Category cat = service.findById(id);
        cat.setId(id);
        cat.setName(categoria.getName());
        cat.setProductosIds(categoria.getProductosIds());

        service.save(cat);
        return ResponseEntity.ok(categoria);
    } catch ( Exception e ) {
        return ResponseEntity.notFound().build();
    }
}

@Operation(
        summary = "Buscar categoría por nombre",
        description = "Busca una categoría específica utilizando su nombre exacto como parámetro de consulta."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoría encontrada con éxito"),
        @ApiResponse(responseCode = "404", description = "No se encontró ninguna categoría con el nombre proporcionado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/search")
    public ResponseEntity<Category> obtenerCategoriaPorNombre(@RequestParam String name) {
        Category category = service.findByName(name);
        return ResponseEntity.ok(category);
    }

}
