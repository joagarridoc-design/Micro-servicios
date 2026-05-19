package com.example.ms_inventario.Service;

import com.example.ms_inventario.Model.Inventory;
import com.example.ms_inventario.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository repository;


    public Inventory findById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public Inventory saveInventarios(Inventory inventarios){
        return repository.save(inventarios);
    }

    public List<Inventory>getInventarios(){
        return repository.findAll();
    }


    public List<Inventory> getInventariosByMes(String mesinv) {
        return repository.findByMesinv(mesinv);
    }

    public List<Inventory> getInventariosByNombre(String nombreinv) {
        return repository.findByNombreinv(nombreinv);
    }
    public void eliminarInventario(Integer id) {
        repository.deleteById(id);
    }
    
    

}