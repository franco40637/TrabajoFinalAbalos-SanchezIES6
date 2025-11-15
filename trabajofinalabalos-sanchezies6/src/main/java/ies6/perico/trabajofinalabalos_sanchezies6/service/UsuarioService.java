package ies6.perico.trabajofinalabalos_sanchezies6.service;

import ies6.perico.trabajofinalabalos_sanchezies6.model.Usuario;
import java.util.List;

public interface UsuarioService {

    // Se usará boolean para manejar errores de duplicidad (similar a VehiculoService)
    boolean guardarUsuario(Usuario usuario); 

    // Listar solo los usuarios activos
    List<Usuario> listarUsuarios(); 

    // Eliminación lógica (similar a VehiculoService)
    void eliminarUsuarioLogico(int id); 
    
    // Para el formulario de Modificar
    Usuario buscarPorId(int id); 
}