package ies6.perico.trabajofinalabalos_sanchezies6.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies6.perico.trabajofinalabalos_sanchezies6.model.Conductor;
import ies6.perico.trabajofinalabalos_sanchezies6.model.Vehiculo;
import ies6.perico.trabajofinalabalos_sanchezies6.repository.ConductorRepository;

@Service
public class ConductorServiceImp implements ConductorService {

    @Autowired
    private ConductorRepository conductorRepository;

    @Override
    public boolean guardarConductor(Conductor conductor) {
        
        // Verificar que DNI sea unico
        Conductor dniExistente = conductorRepository.findByDni(conductor.getDni());
        if (dniExistente != null && (conductor.getId() == 0 || conductor.getId() != dniExistente.getId())) {
            System.out.println("⚠️ Error: DNI duplicado.");
            return false;
        }

        // Verificar Licencia sea unica
        Conductor licenciaExistente = conductorRepository.findByLicencia(conductor.getLicencia());
        if (licenciaExistente != null && (conductor.getId() == 0 || conductor.getId() != licenciaExistente.getId())) {
            System.out.println("⚠️ Error: Licencia duplicada.");
            return false;
        }

        // Verificar si el Vehiculo ya esta asignado a OTRO conductor
        Vehiculo vehiculoAsignado = conductor.getVehiculo();
        if (vehiculoAsignado != null) {
            
            // CORRECCION: El repositorio ahora devuelve una lista
            List<Conductor> conductoresConMismoVehiculo = conductorRepository.findByVehiculo(vehiculoAsignado);
            
            // Si la lista no está vacía, verificamos si alguno de los conductores encontrados
            // NO es el conductor que estamos intentando guardar/modificar (c.getId() != conductor.getId())
            boolean vehiculoYaAsignadoAOtro = conductoresConMismoVehiculo.stream()
                .anyMatch(c -> c.getId() != conductor.getId());
            
            if (vehiculoYaAsignadoAOtro) {
                System.out.println("⚠️ Error: Vehículo ya asignado a otro conductor.");
                return false;
            }
        }
        
        // Si es nuevo, asegurar activo
        if (conductor.getId() == 0) {
            conductor.setActivo(true);
        }

        conductorRepository.save(conductor);
        System.out.println("✅ Conductor guardado: " + conductor.getNombre() + " " + conductor.getApellido());
        return true;
    }

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
}