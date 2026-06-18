package com.example.ms_category.Service;

import com.example.ms_category.Model.Category;
import com.example.ms_category.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category saveCategory(Category category) {
        return repository.save(category);
    }

    public List<Category> getAllCategory() {
        return repository.findAll();
    }

    public Category findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteCategory(Integer id) {
        repository.deleteById(id);
    }
      public Category save(Category categoria) {
        
        return repository.save(categoria);
    }



}
