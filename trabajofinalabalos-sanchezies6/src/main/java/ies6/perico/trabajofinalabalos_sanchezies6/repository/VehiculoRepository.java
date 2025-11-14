package ies6.perico.trabajofinalabalos_sanchezies6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ies6.perico.trabajofinalabalos_sanchezies6.model.Vehiculo;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {
    // Busca si ya existe un vehiculo con esa patente
    Vehiculo findByPatente(String patente);
}