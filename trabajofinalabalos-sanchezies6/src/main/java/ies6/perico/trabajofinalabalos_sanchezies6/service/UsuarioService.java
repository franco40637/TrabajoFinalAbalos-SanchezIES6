package ies6.perico.trabajofinalabalos_sanchezies6.service;

import ies6.perico.trabajofinalabalos_sanchezies6.model.Usuario;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private List<Usuario> usuarios = new ArrayList<>();

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    public void eliminarUsuario(int id) {
        usuarios.removeIf(u -> u.getId() == id);
    }
}
