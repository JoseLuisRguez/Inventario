package com.Inventario.ControldeInventario.Repositorios;
import com.Inventario.ControldeInventario.Entidades.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long>{
    User findByNombreAndPassword(String nombre, String password);
}
