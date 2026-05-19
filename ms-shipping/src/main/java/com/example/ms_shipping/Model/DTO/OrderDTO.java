package com.example.ms_shipping.Model.DTO;
import lombok.Data;

@Data
// DTO (Data Transfer Object) es un objeto simple que se usa solo
// para transportar datos, no para persistirlos, no para lógica,
// y no para relaciones JPA (Define qué datos quiero recibir desde una class).
public class OrderDTO {
    private Integer idorden;
    private String estado;
    private String total;
}