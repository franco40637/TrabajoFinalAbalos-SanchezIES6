package ies6.perico.trabajofinalabalos_sanchezies6.repository;

import ies6.perico.trabajofinalabalos_sanchezies6.model.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ConductorRepository extends JpaRepository<Conductor, Integer> {

    // 1. Metodos para la validacion en el Service (DNI/Licencia)
    Conductor findByDni(String dni);
    Conductor findByLicencia(String licencia);

    // 2. 'findByActivoTrue'
    List<Conductor> findByActivoTrue(); 
}
