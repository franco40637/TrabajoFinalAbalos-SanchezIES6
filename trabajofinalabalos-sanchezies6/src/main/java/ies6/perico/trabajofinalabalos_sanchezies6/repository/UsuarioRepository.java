package ies6.perico.trabajofinalabalos_sanchezies6.repository;

import ies6.perico.trabajofinalabalos_sanchezies6.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsByEmail(String email);
    boolean existsByDni(String dni);
}
