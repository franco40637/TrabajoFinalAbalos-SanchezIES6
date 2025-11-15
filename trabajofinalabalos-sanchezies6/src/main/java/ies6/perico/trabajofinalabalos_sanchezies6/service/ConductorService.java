package ies6.perico.trabajofinalabalos_sanchezies6.service;

import ies6.perico.trabajofinalabalos_sanchezies6.model.Conductor;
import java.util.List;

public interface ConductorService {

    // ESTE METODO DEBE EXISTIR PARA QUE EL CONTROLLER FUNCIONE
    boolean guardarConductor(Conductor conductor); 

    Conductor buscarPorId(int id);
    
    List<Conductor> listarConductores();
    
    void eliminarConductorLogico(int id);
    
    List<Conductor> listarConductoresActivosSinVehiculo();
}