package ies6.perico.trabajofinalabalos_sanchezies6.service;

import ies6.perico.trabajofinalabalos_sanchezies6.model.Viaje;
import ies6.perico.trabajofinalabalos_sanchezies6.repository.ViajeRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViajeServiceImp implements ViajeService {

    @Autowired
    private ViajeRepository viajeRepository;

    // LISTAR VIAJES ACTIVOS
    @Override
    public List<Viaje> listarViajesActivos() {
        return viajeRepository.findByActivoTrue();
    }

    // BUSCAR VIAJE POR ID
    @Override
    public Optional<Viaje> buscarViajePorId(int id) {
        return viajeRepository.findById(id);
    }

    // ELIMINACION LOGICA
    @Override
    public void eliminarViaje(int id) {
        Optional<Viaje> viaje = viajeRepository.findById(id);
        if (viaje.isPresent()) {
            viaje.get().setActivo(false);
            viajeRepository.save(viaje.get());
        }
    }

    // CALCULO AUTOMATICO DEL PRECIO
    private Double calcularCosto(String tipoVehiculo, String tipoDistancia) {
    double costoBase = 0;

    // 1. Base por distancia
    if ("CORTA".equalsIgnoreCase(tipoDistancia) || "MEDIA".equalsIgnoreCase(tipoDistancia)) {
        costoBase = 7000.0;
    } else if ("LARGA".equalsIgnoreCase(tipoDistancia)) { 
        costoBase = 20000.0;
    }

    // 2. Incremento por tipo de vehículo
    if ("LUXE".equalsIgnoreCase(tipoVehiculo)) {
        costoBase *= 1.10; // +10%
    } else if ("PREMIUM".equalsIgnoreCase(tipoVehiculo)) {
        costoBase *= 1.20; // +20%
    }

    return costoBase;
}

    // GUARDA VIAJE (con calculo automatico de precio)
    @Override
    public void guardarViaje(Viaje viaje) {

        if (viaje.getVehiculo() != null && viaje.getTipoDistancia() != null) {

            String tipoV = viaje.getVehiculo().getTipo();
            String tipoD = viaje.getTipoDistancia();

            Double precioFinal = calcularCosto(tipoV, tipoD);

            viaje.setPrecio(precioFinal);

        } else {
            System.err.println("Advertencia: No se pudo calcular el precio porque faltan datos obligatorios.");
        }

        viajeRepository.save(viaje);
    }
}
