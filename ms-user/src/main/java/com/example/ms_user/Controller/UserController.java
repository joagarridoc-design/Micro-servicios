package com.example.ms_user.Controller;

import com.example.ms_user.Model.User;
import com.example.ms_user.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UserController {

    @Autowired
    private UserService service;

    // MÉTODO PARA ESCRIBIR EN LA BD
    @PostMapping
    public ResponseEntity<User> crear(@RequestBody User usuario) {
        // Al guardar el usuario, JPA se encarga de insertar en 'usuario',
        // 'perfil' (por el Cascade) y en 'usuario_juego_ids'.
        User nuevoUsuario = service.save(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerDetalleCompleto(@PathVariable Integer id) {
        /* Map retorna clave : valor */
        Map<String, Object> respuesta = service.buscarUsuarioCompleto(id);

        if (respuesta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(respuesta);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Integer id) {
        service.eliminarUsuario(id);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }
    
}
