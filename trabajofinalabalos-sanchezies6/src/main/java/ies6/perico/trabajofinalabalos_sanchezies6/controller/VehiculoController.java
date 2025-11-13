package ies6.perico.trabajofinalabalos_sanchezies6.controller;

import ies6.perico.trabajofinalabalos_sanchezies6.model.Vehiculo;
import ies6.perico.trabajofinalabalos_sanchezies6.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    // 🔹 Muestra el formulario de carga de vehículos
    @GetMapping("/vehiculo")
    public String mostrarFormularioVehiculo(Model model) {
        model.addAttribute("vehiculo", new Vehiculo());
        return "vehiculo"; // busca vehiculo.html
    }

    // 🔹 Guarda un vehículo nuevo
    @PostMapping("/guardarVehiculo")
    public String guardarVehiculo(@ModelAttribute Vehiculo vehiculo) {
        vehiculoService.guardarVehiculo(vehiculo);
        return "redirect:/listaVehiculos"; // redirige a la lista
    }

    // 🔹 Muestra la lista de vehículos activos
    @GetMapping("/listaVehiculos")
    public String listarVehiculos(Model model) {
        model.addAttribute("listaVehiculos", vehiculoService.listarVehiculos());
        return "listaVehiculos"; // busca listaVehiculos.html
    }

    // 🔹 Elimina lógicamente un vehículo (no se borra de la BD)
       @GetMapping("/eliminarVehiculo/{id}")
    public String eliminarVehiculo(@PathVariable("id") int id) {
        vehiculoService.eliminarVehiculoLogico(id);
        return "redirect:/listaVehiculos";
    }
}