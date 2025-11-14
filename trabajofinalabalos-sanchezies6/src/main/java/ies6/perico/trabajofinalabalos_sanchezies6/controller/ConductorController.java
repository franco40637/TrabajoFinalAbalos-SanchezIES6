package ies6.perico.trabajofinalabalos_sanchezies6.controller;

import ies6.perico.trabajofinalabalos_sanchezies6.model.Conductor;
import ies6.perico.trabajofinalabalos_sanchezies6.repository.ConductorRepository; // NUEVA IMPORTACIÓN
import ies6.perico.trabajofinalabalos_sanchezies6.service.ConductorService;
import ies6.perico.trabajofinalabalos_sanchezies6.service.VehiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConductorController {

    @Autowired
    private ConductorService conductorService;
    
    @Autowired
    private VehiculoService vehiculoService; 
    
    // INYECTAMOS EL REPOSITORIO DIRECTAMENTE
    @Autowired
    private ConductorRepository conductorRepository; // NUEVA INYECCIÓN

    // Método para cargar datos comunes (Vehículos disponibles y Repositorio)
    private void cargarDatosComunes(Model model) {
        model.addAttribute("vehiculosActivos", vehiculoService.listarVehiculos());
        
        // CORRECCIÓN: Pasamos el repositorio al modelo con el nombre 'cRepo'
        model.addAttribute("cRepo", conductorRepository); 
    }

    // 1. Muestra el formulario de carga de conductores (CREAR)
    @GetMapping("/conductor")
    public String mostrarFormularioConductor(Model model) {
        model.addAttribute("conductor", new Conductor());
        cargarDatosComunes(model);
        return "conductor"; // Busca conductor.html
    }

    // 2. Muestra el formulario con datos precargados (MODIFICAR)
    @GetMapping("/modificarConductor/{id}")
    public String modificarConductor(@PathVariable("id") int id, Model model) {
        Conductor conductor = conductorService.buscarPorId(id);
        
        if (conductor == null || !conductor.isActivo()) {
            return "redirect:/listaConductores";
        }
        
        model.addAttribute("conductor", conductor);
        cargarDatosComunes(model); // Carga la lista de vehículos y el repositorio
        return "conductor"; 
    }

    // 3. Guarda o Actualiza un conductor (CREATE y UPDATE)
    @PostMapping("/guardarConductor")
    public String guardarConductor(@Valid @ModelAttribute Conductor conductor, 
                                     BindingResult result, 
                                     Model model) {

        // A. Manejo de errores de validación de campos
        if (result.hasErrors()) {
            cargarDatosComunes(model);
            return "conductor"; 
        }

        // B. Manejo de errores de LÓGICA DE NEGOCIO (DNI, Licencia o Vehículo Duplicado)
        boolean guardado = conductorService.guardarConductor(conductor);

        if (!guardado) {
            cargarDatosComunes(model);
            model.addAttribute("conductor", conductor);
            model.addAttribute("errorLogica", "⚠️ Error de registro: El DNI, Licencia o el Vehículo seleccionado ya están registrados.");
            return "conductor"; 
        }

        return "redirect:/listaConductores";
    }

    // 4. Muestra la lista de conductores activos (READ)
    @GetMapping("/listaConductores")
    public String listarConductores(Model model) {
        model.addAttribute("listaConductores", conductorService.listarConductores());
        return "listaConductores"; // busca listaConductores.html
    }

    // 5. Elimina lógicamente un conductor (DELETE LÓGICO)
    @GetMapping("/eliminarConductor/{id}")
    public String eliminarConductor(@PathVariable("id") int id) {
        conductorService.eliminarConductorLogico(id);
        return "redirect:/listaConductores";
    }
}