package ies6.perico.trabajofinalabalos_sanchezies6.service;

import java.util.List;
import ies6.perico.trabajofinalabalos_sanchezies6.model.Vehiculo;

public interface VehiculoService {

    boolean guardarVehiculo(Vehiculo vehiculo);

    List<Vehiculo> listarVehiculos();

    void eliminarVehiculoLogico(int id);
    
    // 🆕 NUEVO: Método para buscar un vehículo por su ID
    Vehiculo buscarPorId(int id); 
}

