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

    @Override
    public List<Viaje> listarViajesActivos() {
        return viajeRepository.findByActivoTrue();
    }

    @Override
    public Optional<Viaje> buscarViajePorId(int id) {
        return viajeRepository.findById(id);
    }

    private Double calcularCosto(String tipoVehiculo, String tipoDistancia) {

        double costoBase = 0;

        if ("CORTA".equalsIgnoreCase(tipoDistancia) || "MEDIA".equalsIgnoreCase(tipoDistancia)) {
            costoBase = 7000.0;
        } else if ("LARGA".equalsIgnoreCase(tipoDistancia)) {
            costoBase = 20000.0;
        }

        if ("LUXE".equalsIgnoreCase(tipoVehiculo)) {
            costoBase *= 1.10;
        } else if ("PREMIUM".equalsIgnoreCase(tipoVehiculo)) {
            costoBase *= 1.20;
        }

        return costoBase;
    }

    @Override
    public Viaje guardarViaje(Viaje viaje) {

        if (viaje.getVehiculo() != null && viaje.getTipoDistancia() != null) {

            String tipoV = viaje.getVehiculo().getTipo();
            String tipoD = viaje.getTipoDistancia();

            Double precioFinal = calcularCosto(tipoV, tipoD);

            viaje.setPrecio(precioFinal);
        }

        return viajeRepository.save(viaje);
    }

    @Override
    public Optional<Viaje> buscarUltimoViajeActivo() {
        return Optional.ofNullable(viajeRepository.findTopByActivoTrueOrderByIdDesc());
    }
}
