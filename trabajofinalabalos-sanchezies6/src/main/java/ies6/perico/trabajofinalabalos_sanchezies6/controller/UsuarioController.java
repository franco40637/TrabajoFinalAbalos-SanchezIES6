package ies6.perico.trabajofinalabalos_sanchezies6.controller;

import ies6.perico.trabajofinalabalos_sanchezies6.model.Usuario;
import ies6.perico.trabajofinalabalos_sanchezies6.service.UsuarioService;
import jakarta.validation.Valid; // 🆕 Nuevo
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; // 🆕 Nuevo
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService; // 🆕 Usamos el Service

    // 1. Muestra el formulario de registro (Crear)
    @GetMapping("/usuario")
    public String mostrarFormularioUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario"; // busca usuario.html
    }
    
    // 2. Muestra el formulario con datos precargados (Modificar)
    @GetMapping("/modificarUsuario/{id}")
    public String modificarUsuario(@PathVariable("id") int id, Model model) {
        Usuario usuario = usuarioService.buscarPorId(id);
        
        // Manejo de caso en que el usuario no exista o esté inactivo
        if (usuario == null || !usuario.isActivo()) {
            return "redirect:/listaUsuarios";
        }
        
        model.addAttribute("usuario", usuario);
        // Reutiliza la misma plantilla HTML para la edición
        return "usuario"; 
    }


    // 3. Guarda o Actualiza un usuario
    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@Valid @ModelAttribute Usuario usuario, // 🆕 Validación de campos
                                 BindingResult result, 
                                 Model model) {

        // A. Manejo de errores de validación de campos (@NotBlank, @Pattern, etc.)
        if (result.hasErrors()) {
            return "usuario"; 
        }

        // B. Manejo de errores de LÓGICA DE NEGOCIO (DNI/Email Duplicado)
        boolean guardado = usuarioService.guardarUsuario(usuario);

        if (!guardado) {
            // El Service ha devuelto 'false' por duplicidad
            model.addAttribute("usuario", usuario);
            model.addAttribute("errorDuplicado", 
                "⚠️ Ya existe un usuario registrado con el DNI o Email ingresado.");
            return "usuario"; 
        }

        return "redirect:/listaUsuarios";
    }

    // 4. Muestra la lista de usuarios activos (READ)
    @GetMapping("/listaUsuarios")
    public String listaUsuarios(Model model) {
        // Lista solo los usuarios activos
        model.addAttribute("listaUsuarios", usuarioService.listarUsuarios()); 
        return "listaUsuarios"; // busca listaUsuarios.html
    }

    // 5. Elimina lógicamente un usuario (DELETE LÓGICO)
    @GetMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable("id") int id) {
        usuarioService.eliminarUsuarioLogico(id); // 🆕 Eliminación lógica
        return "redirect:/listaUsuarios";
    }
}