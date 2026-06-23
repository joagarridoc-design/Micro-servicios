package com.example.ms_payment.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.ms_payment.Model.DTO.UserDTO;
import com.example.ms_payment.Client.UserFeignClient;
import com.example.ms_payment.Model.Payment;
import com.example.ms_payment.Repository.PaymentRepository;



@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private UserFeignClient client;

    public Payment save(Payment payment) {
        
        return repository.save(payment);
    }


    public Payment findById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public Payment savePagos(Payment pagos){
        return repository.save(pagos);
    }

    public List<Payment>getPagos(){
        return repository.findAll();
    }


    public List<Payment> getPaymentsByTipo(String tipo) {
        return repository.findByTipo(tipo);

    }

    public Map<String, Object> getPaymentWithUser(Integer paymentId) {
    Payment payment = repository.findById(paymentId).orElse(null);

    
     Integer userId = payment.getUserIds().get(0);
     UserDTO user = client.obtenerUsuarioPorId(userId);


     Map<String, Object> result = new HashMap<>();
     result.put("idpago", payment.getIdpago());
     result.put("tipo", payment.getTipo());
     result.put("monto", payment.getMonto());
     result.put("usuario", user);

     return result;
    }

    public void eliminarPago(Integer id) {
        repository.deleteById(id);
    }

    

  
    

  

}

