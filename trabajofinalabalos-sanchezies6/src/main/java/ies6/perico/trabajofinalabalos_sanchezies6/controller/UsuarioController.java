package ies6.perico.trabajofinalabalos_sanchezies6.controller;

import ies6.perico.trabajofinalabalos_sanchezies6.model.Usuario;
import ies6.perico.trabajofinalabalos_sanchezies6.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // 🟩 Mostrar formulario de registro
    @GetMapping("/usuario")
    public String mostrarFormularioUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("mensajeError", null);
        return "usuario";
    }

    // 🟩 Guardar usuario validando duplicados
    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario, Model model) {

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            model.addAttribute("usuario", usuario);
            model.addAttribute("mensajeError", "⚠️ El email ya está registrado. Intente con otro.");
            return "usuario";
        }

        if (usuarioRepository.existsByDni(usuario.getDni())) {
            model.addAttribute("usuario", usuario);
            model.addAttribute("mensajeError", "⚠️ El DNI ya está registrado. Verifique los datos.");
            return "usuario";
        }

        usuarioRepository.save(usuario);
        return "redirect:/listaUsuarios";
    }

    // 🟩 Mostrar lista de usuarios
    @GetMapping("/listaUsuarios")
    public String listaUsuarios(Model model) {
        model.addAttribute("listaUsuarios", usuarioRepository.findAll());
        return "listaUsuarios";
    }

    // 🟥 Eliminar usuario
    @GetMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable("id") int id) {
        usuarioRepository.deleteById(id);
        return "redirect:/listaUsuarios";
    }
}

