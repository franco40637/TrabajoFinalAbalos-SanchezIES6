package ies6.perico.trabajofinalabalos_sanchezies6.service;

import ies6.perico.trabajofinalabalos_sanchezies6.model.Viaje;
import java.util.List;
import java.util.Optional;

public interface ViajeService {

    // Metodos especificos de Viaje
    List<Viaje> listarViajesActivos();
    Optional<Viaje> buscarViajePorId(int id);
    void guardarViaje(Viaje viaje);
    void eliminarViaje(int id);
}
