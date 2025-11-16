package ies6.perico.trabajofinalabalos_sanchezies6.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies6.perico.trabajofinalabalos_sanchezies6.model.Vehiculo;
import ies6.perico.trabajofinalabalos_sanchezies6.repository.VehiculoRepository;

@Service
public class VehiculoServiceImp implements VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    // VehiculoServiceImp.java

@Override
public boolean guardarVehiculo(Vehiculo vehiculo) {
    Vehiculo existente = vehiculoRepository.findByPatente(vehiculo.getPatente());
    
    // Si encontramos un vehiculo con la misma patente
    if (existente != null) {
        
        if (existente.getId() != vehiculo.getId()) { 
            System.out.println("⚠️ Ya existe un vehículo con la patente " + vehiculo.getPatente());
            return false; // Error: Patente duplicada.
        }
        
    }
    
    
    if (vehiculo.getId() == 0) { 
        vehiculo.setActivo(true);
    }

    // Aquí se guarda. vehiculoRepository.save(vehiculo) devuelve el objeto guardado 
    vehiculoRepository.save(vehiculo);
    System.out.println("✅ Vehículo guardado: " + vehiculo.getMarca() + " " + vehiculo.getModelo());
    return true;
}

    // Listar vehículos activos
    @Override
    public List<Vehiculo> listarVehiculos() {
        // CONTROL: Lista solo los vehículos activos
        return vehiculoRepository.findAll()
                .stream()
                .filter(Vehiculo::isActivo)
                .collect(Collectors.toList());
    }

    // Eliminar LÓGICAMENTE
    @Override
    public void eliminarVehiculoLogico(int id) {
        Vehiculo v = vehiculoRepository.findById(id).orElse(null);
        if (v != null && v.isActivo()) {
            v.setActivo(false);
            vehiculoRepository.save(v);
            System.out.println("🗑️ Vehículo eliminado lógicamente: " + v.getPatente());
        } else {
            System.out.println("⚠️ No se encontró el vehículo activo con ID: " + id);
        }
    }
    
    @Override
    public Vehiculo buscarPorId(int id) {
        return vehiculoRepository.findById(id).orElse(null);
    }
}