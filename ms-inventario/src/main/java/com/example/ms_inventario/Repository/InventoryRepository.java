package com.example.ms_inventario.Repository;

import com.example.ms_inventario.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    List<Inventory> findByMesinv(String mesinv);

    List<Inventory> findByNombreinv(String nombreinv);

}
