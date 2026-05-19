package com.example.ms_category.Controller;

import com.example.ms_category.Model.Category;
import com.example.ms_category.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    public CategoryController(CategoryService category) {
        this.service = category;
    }

    @PostMapping
    public Category guardarCategory(@RequestBody Category category) {
        return service.saveCategory(category);
    }

    @GetMapping
    public List<Category> listarTodas() {
        return service.getAllCategory();
    }

    @DeleteMapping("/{id}")
    public void eliminarCategory(@PathVariable Integer id) {
        service.deleteCategory(id);
    }

}
