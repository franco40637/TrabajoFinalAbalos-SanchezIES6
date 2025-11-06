package ies6.perico.trabajofinalabalos_sanchezies6.controller;

import ies6.perico.trabajofinalabalos_sanchezies6.model.Usuario;
import ies6.perico.trabajofinalabalos_sanchezies6.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.agregarUsuario(usuario); 
        return "redirect:/listaUsuarios";
    }


    @GetMapping("/listaUsuarios")
    public String mostrarLista(Model model) {
        model.addAttribute("listaUsuarios", usuarioService.listarUsuarios());
        return "listaUsuarios";
    }
}
