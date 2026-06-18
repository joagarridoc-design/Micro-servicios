package com.example.ms_sucursal.Service;

import com.example.ms_sucursal.Model.Sucursal;
import com.example.ms_sucursal.Repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SucursalService {

    @Autowired
    private SucursalRepository repository;

    public Sucursal save(Sucursal sucursal) {
        
        return repository.save(sucursal);
    }


    public Sucursal findById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public Sucursal saveSucursales(Sucursal sucursales){
        return repository.save(sucursales);
    }

    public List<Sucursal>getSucursales(){
        return repository.findAll();
    }


    public List<Sucursal> getSucursalByRegion(String region) {
        return repository.findByRegion(region);
    }

    public List<Sucursal> getSucursalesByNombre(String comuna) {
        return repository.findByComuna(comuna);
    }
    public void eliminarSucursal(Integer id) {
        repository.deleteById(id);
    }

}
