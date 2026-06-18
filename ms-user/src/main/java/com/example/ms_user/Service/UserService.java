package com.example.ms_user.Service;
import com.example.ms_user.Client.ProductoFeignClient;
import com.example.ms_user.Model.DTO.ProductoDTO;
import com.example.ms_user.Model.User;
import com.example.ms_user.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service

public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ProductoFeignClient productoClient;


    public User save(User usuario) {
        
        if(usuario.getPerfil() != null) {
            usuario.getPerfil().setUsuario(usuario);
        }
        return repository.save(usuario);
    }

    public Map<String, Object> buscarUsuarioCompleto(Integer id) {
        User usuario = repository.findById(id).orElse(null);
        // Para obtener una respuesta en formato clave: valor
        Map<String, Object> respuesta = new HashMap<>();

        if (usuario != null) {
            // Por cada ID de juego que tiene el usuario, llamamos al otro microservicio
            List<ProductoDTO> productoDetalle = usuario.getProductosIds().stream()
                    .map(productoId -> productoClient.obtenerProductoPorId(productoId))
                    .collect(Collectors.toList());

            respuesta.put("id", usuario.getId());
            respuesta.put("nombre", usuario.getNombre());
            respuesta.put("perfil", usuario.getPerfil());
            // Aquí van los objetos completos del otro MS
            respuesta.put("productoFavoritos", productoDetalle);
        }
        return respuesta;
    }
    
    public void eliminarUsuario(Integer id) {
        repository.deleteById(id);
    }

    public User findById(Integer id){
        return repository.findById(id).orElse(null);
    }
   
    
}