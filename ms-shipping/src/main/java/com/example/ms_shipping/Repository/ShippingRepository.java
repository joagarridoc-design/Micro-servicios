package com.example.ms_shipping.Repository;

import com.example.ms_shipping.Model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ShippingRepository extends JpaRepository<Shipping, Integer> {
    List<Shipping> findByMesinicio(String mesinicio);

    List<Shipping> findByMesllegada(String mesllegada);
}
