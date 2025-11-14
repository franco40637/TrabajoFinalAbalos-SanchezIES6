package ies6.perico.trabajofinalabalos_sanchezies6.service;

import java.util.List;

import ies6.perico.trabajofinalabalos_sanchezies6.model.Conductor;

public interface ConductorService {
    
    boolean guardarConductor(Conductor conductor);

    List<Conductor> listarConductores();

    Conductor buscarPorId(int id);

    void eliminarConductorLogico(int id);
}