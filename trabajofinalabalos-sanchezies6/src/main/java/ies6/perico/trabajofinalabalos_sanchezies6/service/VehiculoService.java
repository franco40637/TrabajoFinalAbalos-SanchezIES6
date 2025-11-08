package ies6.perico.trabajofinalabalos_sanchezies6.service;

import java.util.List;
import ies6.perico.trabajofinalabalos_sanchezies6.model.Vehiculo;

public interface VehiculoService {

    void guardarVehiculo(Vehiculo vehiculo);

    List<Vehiculo> listarVehiculos();

    void eliminarVehiculoLogico(int id);
}
