package com.example.ms_sucursal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ms_sucursal.Model.Sucursal;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Integer>{

    List<Sucursal> findByRegion(String region);

    List<Sucursal> findByComuna(String comuna);

}
