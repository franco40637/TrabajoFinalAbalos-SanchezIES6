package ies6.perico.trabajofinalabalos_sanchezies6.service;

import java.util.List;
import ies6.perico.trabajofinalabalos_sanchezies6.model.Vehiculo;

public interface VehiculoService {

    // ✅ Devuelve true si el vehículo fue guardado, false si la patente ya existe
    boolean guardarVehiculo(Vehiculo vehiculo);

    // ✅ Lista solo los vehículos activos
    List<Vehiculo> listarVehiculos();

    // ✅ Eliminación lógica (no borra de la BD)
    void eliminarVehiculoLogico(int id);
}

