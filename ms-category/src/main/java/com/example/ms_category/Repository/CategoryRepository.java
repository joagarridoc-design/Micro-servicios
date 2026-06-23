package com.example.ms_category.Repository;

import com.example.ms_category.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

    Category findByName(String name);

}
