package com.example.ms_shipping.Service;

import com.example.ms_shipping.Model.Shipping;
import com.example.ms_shipping.Repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShippingService {

    @Autowired
    private ShippingRepository repository;


    public Shipping findById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public Shipping saveEnvios(Shipping envios){
        return repository.save(envios);
    }

    public List<Shipping>getEnvios(){
        return repository.findAll();
    }


    public List<Shipping> getShippingByMesinicio(String mesinicio) {
        return repository.findByMesinicio(mesinicio);
    }
    
    public List<Shipping> getShippingByMesllegada(String mesllegada) {
        return repository.findByMesllegada(mesllegada);
    }
    public void eliminarEnvio(Integer id) {
        repository.deleteById(id);
    }

}