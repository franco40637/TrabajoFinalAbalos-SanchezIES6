package ies6.perico.trabajofinalabalos_sanchezies6.service;

import ies6.perico.trabajofinalabalos_sanchezies6.model.Usuario;
import java.util.List;
import java.util.Optional; 

public interface UsuarioService {

    boolean guardarUsuario(Usuario usuario); 

    List<Usuario> listarUsuariosActivos(); 

    // 'eliminarUsuarioLogico' 
    void eliminarUsuarioLogico(int id); 
    
    // Usamos Optional<Usuario> para un manejo de errores 
    Optional<Usuario> buscarUsuarioPorId(int id); 
}