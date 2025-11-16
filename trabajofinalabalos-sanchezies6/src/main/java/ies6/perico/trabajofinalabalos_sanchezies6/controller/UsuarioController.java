package ies6.perico.trabajofinalabalos_sanchezies6.controller;

import ies6.perico.trabajofinalabalos_sanchezies6.model.Usuario;
import ies6.perico.trabajofinalabalos_sanchezies6.service.UsuarioService;
import jakarta.validation.Valid; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; 
import org.springframework.web.bind.annotation.*;

import java.util.Optional; //Necesario para manejar el Optional del servicio

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService; 

    // 1. Muestra el formulario de registro
    @GetMapping("/usuario")
    public String mostrarFormularioUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario"; // busca usuario.html
    }
    
    // 2. Muestra el formulario con datos precargados (Modificar)
    @GetMapping("/modificarUsuario/{id}")
    public String modificarUsuario(@PathVariable("id") int id, Model model) {
        
        //  nombre y el tipo de retorno Optional
        Optional<Usuario> usuarioOptional = usuarioService.buscarUsuarioPorId(id);
        
        // Manejo de caso: Si no existe el Optional o si el usuario no está activo
        if (usuarioOptional.isEmpty() || !usuarioOptional.get().isActivo()) {
            return "redirect:/listaUsuarios";
        }
        
        // Obtenemos el objeto Usuario del Optional
        Usuario usuario = usuarioOptional.get();
        
        model.addAttribute("usuario", usuario);
        return "usuario"; 
    }


    // 3. Guarda o Actualiza un usuario
    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@Valid @ModelAttribute Usuario usuario, 
                                 BindingResult result, 
                                 Model model) {

        // A. Manejo de errores de validacion de campos (@NotBlank, @Pattern, etc.)
        if (result.hasErrors()) {
            return "usuario"; 
        }

        // B. Manejo de errores de LOGICA DE NEGOCIO (DNI/Email Duplicado)
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

    // 4. Muestra la lista de usuarios activos
    @GetMapping("/listaUsuarios")
    public String listaUsuarios(Model model) {
        // Lista solo los usuarios activos
        model.addAttribute("listaUsuarios", usuarioService.listarUsuariosActivos()); 
        return "listaUsuarios"; // busca listaUsuarios.html
    }

    // 5. Elimina lógicamente un usuario
    @GetMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable("id") int id) {
        usuarioService.eliminarUsuarioLogico(id); 
        return "redirect:/listaUsuarios";
    }
}