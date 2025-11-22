package ies6.perico.trabajofinalabalos_sanchezies6.controller;

import ies6.perico.trabajofinalabalos_sanchezies6.model.Viaje;
import ies6.perico.trabajofinalabalos_sanchezies6.service.ViajeService;
import ies6.perico.trabajofinalabalos_sanchezies6.service.UsuarioService;
import ies6.perico.trabajofinalabalos_sanchezies6.service.ConductorService;
import ies6.perico.trabajofinalabalos_sanchezies6.service.VehiculoService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class ViajeController {

    @Autowired
    private ViajeService viajeService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ConductorService conductorService;

    @Autowired
    private VehiculoService vehiculoService;

    private void cargarEntidades(Model model) {
        model.addAttribute("usuariosActivos", usuarioService.listarUsuariosActivos());
        model.addAttribute("conductoresActivos", conductorService.listarConductores());
        model.addAttribute("vehiculosActivos", vehiculoService.listarVehiculos());
    }

    @GetMapping("/viaje")
    public String mostrarFormularioViaje(Model model) {
        model.addAttribute("viaje", new Viaje());
        cargarEntidades(model);
        return "viaje";
    }

    @PostMapping("/guardarViaje")
    public String guardarViaje(
            @Valid @ModelAttribute("viaje") Viaje viaje,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            cargarEntidades(model);
            return "viaje";
        }

        viajeService.guardarViaje(viaje);

        return "redirect:/reciboViaje";
    }

    @GetMapping("/reciboViaje")
    public String mostrarRecibo(Model model) {

        Viaje viaje = viajeService.buscarUltimoViajeActivo().orElse(null);

        if (viaje == null) {
            return "redirect:/";
        }

        model.addAttribute("esRecibo", true);
        model.addAttribute("reciboViaje", Collections.singletonList(viaje));

        return "reciboViaje";
    }
}
