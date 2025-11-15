package ies6.perico.trabajofinalabalos_sanchezies6.controller;

import ies6.perico.trabajofinalabalos_sanchezies6.model.Vehiculo;
import ies6.perico.trabajofinalabalos_sanchezies6.service.ConductorService; // AÑADIDO
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
public class VehiculoController {

	@Autowired
	private VehiculoService vehiculoService;

    @Autowired // AÑADIDO: Necesitamos el servicio de conductor para la lista desplegable
    private ConductorService conductorService; 

	// 1. Muestra el formulario de carga de vehículos (Para CREAR un registro nuevo)
	@GetMapping("/vehiculo")
	public String mostrarFormularioVehiculo(Model model) {
		model.addAttribute("vehiculo", new Vehiculo());
        // AÑADIDO: Pasar la lista de conductores disponibles
        model.addAttribute("conductoresDisponibles", conductorService.listarConductoresActivosSinVehiculo()); 
		return "vehiculo"; // busca vehiculo.html
	}

	// 2. Muestra el formulario con datos precargados (Para MODIFICAR un registro existente)
	@GetMapping("/modificarVehiculo/{id}")
	public String modificarVehiculo(@PathVariable("id") int id, Model model) {
		// Usa el Service para buscar el vehículo por el ID
		Vehiculo vehiculo = vehiculoService.buscarPorId(id);
		
		// Manejo de caso en que el vehículo no exista o esté inactivo
		if (vehiculo == null || !vehiculo.isActivo()) {
			return "redirect:/listaVehiculos";
		}
		
		model.addAttribute("vehiculo", vehiculo);
        // AÑADIDO: Pasar la lista de conductores disponibles
        // Nota: Se debe incluir al conductor actualmente asignado, incluso si tiene el flag "sin vehículo" en la DB.
        // Asumo que el servicio maneja esto correctamente.
        model.addAttribute("conductoresDisponibles", conductorService.listarConductoresActivosSinVehiculo());
        
		// Reutiliza la misma plantilla HTML para la edición
		return "vehiculo"; 
	}


	// 3. Guarda o Actualiza un vehículo (PostMapping maneja ambos: CREATE y UPDATE)
	@PostMapping("/guardarVehiculo")
	public String guardarVehiculo(@Valid @ModelAttribute Vehiculo vehiculo, 
								 BindingResult result, 
								 Model model) {

		// A. Manejo de errores de validación de campos (@NotBlank, @Min, etc.)
		if (result.hasErrors()) {
            // AÑADIDO: Necesario para que el select de Conductores siga funcionando en caso de error
            model.addAttribute("conductoresDisponibles", conductorService.listarConductoresActivosSinVehiculo()); 
			return "vehiculo"; 
		}

		// B. Manejo de errores de LÓGICA DE NEGOCIO (Patente Duplicada)
		boolean guardado = vehiculoService.guardarVehiculo(vehiculo);

		if (!guardado) {
			// El Service ha devuelto 'false' porque la patente está duplicada (por otro vehículo).
			model.addAttribute("vehiculo", vehiculo);
			model.addAttribute("errorPatente", "⚠️ Ya existe un vehículo registrado con la patente ingresada.");
            // AÑADIDO: Necesario para que el select de Conductores siga funcionando en caso de error
            model.addAttribute("conductoresDisponibles", conductorService.listarConductoresActivosSinVehiculo()); 
			return "vehiculo"; 
		}

		return "redirect:/listaVehiculos";
	}

	// 4. Muestra la lista de vehículos activos (READ)
	@GetMapping("/listaVehiculos")
	public String listarVehiculos(Model model) {
		model.addAttribute("listaVehiculos", vehiculoService.listarVehiculos());
		return "listaVehiculos"; // busca listaVehiculos.html
	}

	// 5. Elimina lógicamente un vehículo (DELETE LÓGICO)
	@GetMapping("/eliminarVehiculo/{id}")
	public String eliminarVehiculo(@PathVariable("id") int id) {
		vehiculoService.eliminarVehiculoLogico(id);
		return "redirect:/listaVehiculos";
	}
}