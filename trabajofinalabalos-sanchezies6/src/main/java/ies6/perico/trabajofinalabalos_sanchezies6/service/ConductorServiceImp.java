package ies6.perico.trabajofinalabalos_sanchezies6.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies6.perico.trabajofinalabalos_sanchezies6.model.Conductor;
import ies6.perico.trabajofinalabalos_sanchezies6.repository.ConductorRepository;

@Service
public class ConductorServiceImp implements ConductorService {

    @Autowired
    private ConductorRepository conductorRepository;

    @Override
    public boolean guardarConductor(Conductor conductor) {
        
        // 1. Verificar que DNI sea unico (Incluye validación para nuevos registros o modificaciones)
        Conductor dniExistente = conductorRepository.findByDni(conductor.getDni());
        if (dniExistente != null && (conductor.getId() == 0 || conductor.getId() != dniExistente.getId())) {
            System.out.println("⚠️ Error: DNI duplicado.");
            return false;
        }

        // 2. Verificar Licencia sea unica (Incluye validación para nuevos registros o modificaciones)
        Conductor licenciaExistente = conductorRepository.findByLicencia(conductor.getLicencia());
        if (licenciaExistente != null && (conductor.getId() == 0 || conductor.getId() != licenciaExistente.getId())) {
            System.out.println("⚠️ Error: Licencia duplicada.");
            return false;
        }
        
        // Si es nuevo, asegurar activo
        if (conductor.getId() == 0) {
            conductor.setActivo(true);
        }

        conductorRepository.save(conductor);
        System.out.println("✅ Conductor guardado: " + conductor.getNombre() + " " + conductor.getApellido());
        return true;
    }

    // -------------------------------------------------------------
    // MÉTODOS EXISTENTES
    // -------------------------------------------------------------

    @Override
    public List<Conductor> listarConductores() {
        // Devuelve solo los conductores activos
        return conductorRepository.findAll()
                .stream()
                .filter(Conductor::isActivo)
                .collect(Collectors.toList());
    }

    @Override
    public Conductor buscarPorId(int id) {
        return conductorRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarConductorLogico(int id) {
        Conductor c = conductorRepository.findById(id).orElse(null);
        if (c != null && c.isActivo()) {
            c.setActivo(false);
            conductorRepository.save(c);
            System.out.println("🗑️ Conductor eliminado lógicamente: " + c.getDni());
        } else {
            System.out.println("⚠️ No se encontró el conductor activo con ID: " + id);
        }
    }
    
    @Override
    public List<Conductor> listarConductoresActivosSinVehiculo() {
        
        List<Conductor> todosConductoresActivos = conductorRepository.findByActivoTrue();
        
        return todosConductoresActivos; 
    }
}