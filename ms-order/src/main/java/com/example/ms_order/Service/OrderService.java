package com.example.ms_order.Service;

import com.example.ms_order.Model.Orders;
import com.example.ms_order.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;


    public Orders findById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public Orders saveOrdenes(Orders ordenes){
        return repository.save(ordenes);
    }

    public List<Orders>getOrdenes(){
        return repository.findAll();
    }


    public List<Orders> getOrderByEstado(String estado) {
        return repository.findByEstado(estado);
    }
    public void eliminarOrden(Integer id) {
        repository.deleteById(id);
    }
    public Orders save(Orders orden) {
        
     
        
        return repository.save(orden);
    }
    

}
