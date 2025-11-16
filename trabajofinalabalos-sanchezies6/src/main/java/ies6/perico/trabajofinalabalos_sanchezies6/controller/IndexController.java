package ies6.perico.trabajofinalabalos_sanchezies6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ies6.perico.trabajofinalabalos_sanchezies6.service.VehiculoService;

@Controller
public class IndexController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping("/")
    public String index(Model model) {
        // Carga los vehiculos activos desde la base de datos
        model.addAttribute("vehiculos", vehiculoService.listarVehiculos());
        return "index"; // Busca index.html en templates
    }
}
