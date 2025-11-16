package ies6.perico.trabajofinalabalos_sanchezies6.repository;

import ies6.perico.trabajofinalabalos_sanchezies6.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Busca si ya existe un usuario con ese email (para validación)
    Usuario findByEmail(String email);
    // Busca si ya existe un usuario con ese DNI (para validación)
    Usuario findByDni(String dni);
    //Buscar todos los usuarios activos
    List<Usuario> findByActivoTrue(); 
    
}