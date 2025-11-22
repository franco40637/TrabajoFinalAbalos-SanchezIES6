package ies6.perico.trabajofinalabalos_sanchezies6.repository;

import ies6.perico.trabajofinalabalos_sanchezies6.model.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Integer> {
    
    // Metodo para listar solo los viajes activos
    List<Viaje> findByActivoTrue();
    Viaje findTopByActivoTrueOrderByIdDesc();
}