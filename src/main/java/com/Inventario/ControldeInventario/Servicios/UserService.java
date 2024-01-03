package com.Inventario.ControldeInventario.Servicios;

import com.Inventario.ControldeInventario.Entidades.User;
import com.Inventario.ControldeInventario.Repositorios.UserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
    
@Service
public class UserService {
    @Autowired
    private UserRepository usuarioRepository;
    
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public User login(String nombre, String password) {
        return usuarioRepository.findByNombreAndPassword(nombre, password);
    }
    
    //Ahora ejecutamos los metodos
    public List<User> obtenerTodosLosUsuarios(){
        return usuarioRepository.findAll();
    }
    
    //saber si se inserto de manera correcta en la base de datos
    public boolean insertarUsuario (User a){
        boolean respuesta = false;
        try{
            usuarioRepository.save(a); //Insert o Update a nivel de BD
            respuesta = true;
        }catch (Exception ex){
            respuesta = false;
        }
        return respuesta;
    }
    
    public boolean actualizarUsuario (User a){
        boolean respuesta = false;
        try{
            usuarioRepository.save(a); //Insert o Update a nivel de BD
            respuesta = true;
        }catch (Exception ex){
            respuesta = false;
        }
        return respuesta;
    }
    
    public boolean eliminarUsuario (User a){
        boolean respuesta = false;
        try{
            usuarioRepository.delete(a); //Delete a nivel de BD
            respuesta = true;
        }catch (Exception ex){
            respuesta = false;
        }
        return respuesta;
    }
    
    // Método para obtener un usuario por ID
    public Optional<User> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }
    
    @Transactional
    public boolean eliminarUsuarios(Long id) {
    try {
        // Lógica para eliminar el usuario por ID
        usuarioRepository.deleteById(id);
        return true; // La eliminación fue exitosa
    } catch (EmptyResultDataAccessException e) {
        // El usuario con el ID proporcionado no existe
        return false; // La eliminación no fue exitosa
    } catch (Exception e) {
        // Manejo de otras excepciones (registra el error si es necesario)
        log.error("Error al eliminar usuario con ID: {}", id, e);
        return false; // La eliminación no fue exitosa
    }
    }
    
    
}
