package com.example.ms_product;

import com.example.ms_product.Model.Productos;
import com.example.ms_product.Repository.ProductosRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductosRepository productosRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

       
        for (int i = 0; i < 20; i++) {
            Productos producto = new Productos();

         
            producto.setNombre(faker.commerce().productName());

            
            int cantidadCategorias = random.nextInt(3) + 1; 
            List<Integer> categoriasAleatorias = new ArrayList<>();
            
            for (int j = 0; j < cantidadCategorias; j++) {
                Integer idCategoria = faker.number().numberBetween(1, 11); 
                
                
                if(!categoriasAleatorias.contains(idCategoria)) {
                    categoriasAleatorias.add(idCategoria);
                }
            }
            
            producto.setCategoriasIds(categoriasAleatorias);

            
            productosRepository.save(producto);
        }

        System.out.println("Datos de prueba de Productos cargados");
    }
}