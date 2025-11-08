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

    @Override
    // CONTROL: Guarda un vehículo solo si no existe la patente
    public void guardarVehiculo(Vehiculo vehiculo) {
        Vehiculo existente = vehiculoRepository.findByPatente(vehiculo.getPatente());

        if (existente == null) {
            vehiculo.setActivo(true); // marca como activo al crearlo
            vehiculoRepository.save(vehiculo);
            System.out.println("✅ Vehículo guardado: " + vehiculo.getMarca() + " " + vehiculo.getModelo());
        } else {
            System.out.println("⚠️ Ya existe un vehículo con la patente " + vehiculo.getPatente());
        }
    }

    @Override
    // CONTROL: Lista solo los vehículos activos (no eliminados)
    public List<Vehiculo> listarVehiculos() {
        return vehiculoRepository.findAll()
                .stream()
                .filter(Vehiculo::isActivo)
                .collect(Collectors.toList());
    }

    @Override
    // ELIMINAR LÓGICAMENTE: no se borra, solo se marca como inactivo
    public void eliminarVehiculoLogico(int id) {
        Vehiculo v = vehiculoRepository.findById(id).orElse(null);
        if (v != null && v.isActivo()) {
            v.setActivo(false);
            vehiculoRepository.save(v);
            System.out.println("🗑️ Vehículo eliminado lógicamente: " + v.getPatente());
        } else {
            System.out.println("⚠️ No se encontró el vehículo con ID: " + id);
        }
    }
}
