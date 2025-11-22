package ies6.perico.trabajofinalabalos_sanchezies6.service;

import ies6.perico.trabajofinalabalos_sanchezies6.model.Viaje;
import java.util.List;
import java.util.Optional;

public interface ViajeService {

    List<Viaje> listarViajesActivos();
    Optional<Viaje> buscarViajePorId(int id);
    Optional<Viaje> buscarUltimoViajeActivo();

    Viaje guardarViaje(Viaje viaje);
}
