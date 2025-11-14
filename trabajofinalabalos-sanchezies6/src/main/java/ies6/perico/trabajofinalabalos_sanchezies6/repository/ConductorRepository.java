package ies6.perico.trabajofinalabalos_sanchezies6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ies6.perico.trabajofinalabalos_sanchezies6.model.Conductor;
import ies6.perico.trabajofinalabalos_sanchezies6.model.Vehiculo;
import java.util.List; // Importar List

@Repository
public interface ConductorRepository extends JpaRepository<Conductor, Integer> {
    
    // Metodo para buscar por DNI
    Conductor findByDni(String dni);

    // Metodo para buscar por Licencia
    Conductor findByLicencia(String licencia);
    
    // METODO CORREGIDO: ahora devuelve una lista para que funcione la verificación en HTML
    List<Conductor> findByVehiculo(Vehiculo vehiculo);
}
