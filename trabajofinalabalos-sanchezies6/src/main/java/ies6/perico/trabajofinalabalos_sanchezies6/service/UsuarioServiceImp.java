package ies6.perico.trabajofinalabalos_sanchezies6.service;

import ies6.perico.trabajofinalabalos_sanchezies6.model.Usuario;
import ies6.perico.trabajofinalabalos_sanchezies6.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional; 

@Service
public class UsuarioServiceImp implements UsuarioService { 

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public boolean guardarUsuario(Usuario usuario) {
        
        // 1. Verificar si el email ya existe
        Usuario emailExistente = usuarioRepository.findByEmail(usuario.getEmail());
        if (emailExistente != null && (usuario.getId() == 0 || usuario.getId() != emailExistente.getId())) {
            System.out.println("⚠️ Email duplicado: " + usuario.getEmail());
            return false;
        }

        // 2. Verificar si el DNI ya existe
        Usuario dniExistente = usuarioRepository.findByDni(usuario.getDni());
        if (dniExistente != null && (usuario.getId() == 0 || usuario.getId() != dniExistente.getId())) {
            System.out.println("⚠️ DNI duplicado: " + usuario.getDni());
            return false;
        }
        
        // Si es un usuario nuevo, asegura que esté activo
        if (usuario.getId() == 0) {
            usuario.setActivo(true);
        }

        usuarioRepository.save(usuario);
        System.out.println("✅ Usuario guardado: " + usuario.getNombre() + " " + usuario.getApellido());
        return true;
    }

    @Override
    public List<Usuario> listarUsuariosActivos() {
        return usuarioRepository.findByActivoTrue();
    }

    @Override
    public void eliminarUsuarioLogico(int id) {
        Usuario u = usuarioRepository.findById(id).orElse(null);
        if (u != null && u.isActivo()) {
            u.setActivo(false);
            usuarioRepository.save(u);
            System.out.println("🗑️ Usuario eliminado lógicamente: " + u.getDni());
        } else {
            System.out.println("⚠️ No se encontró el usuario activo con ID: " + id);
        }
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorId(int id) { 
        // Usamos el método de JPA que devuelve Optional
        return usuarioRepository.findById(id); 
    }
}