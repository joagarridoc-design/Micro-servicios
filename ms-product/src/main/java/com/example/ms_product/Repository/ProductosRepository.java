package com.example.ms_product.Repository;

import com.example.ms_product.Model.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Integer>{

    
}
