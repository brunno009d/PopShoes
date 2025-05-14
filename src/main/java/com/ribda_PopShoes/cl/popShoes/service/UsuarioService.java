package com.ribda_PopShoes.cl.popShoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ribda_PopShoes.cl.popShoes.repository.UsuarioRepository;
import com.ribda_PopShoes.cl.popShoes.model.Usuario;;;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario obtenerUsuarioPorId(Long id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario guardarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

    public Usuario actualizUsuario(Long id, Usuario usuario){
        Usuario usuarioEx = usuarioRepository.findById(id).orElse(null);
        if (usuarioEx != null){
            usuarioEx.setNombre(usuario.getNombre());
            usuarioEx.setApaterno(usuario.getApaterno());
            usuarioEx.setUsuario(usuario.getUsuario());
            usuarioEx.setContraseña(usuario.getContraseña());
            usuarioEx.setDireccion(usuario.getDireccion());
            usuarioEx.setTelefono(usuario.getTelefono());
            usuarioEx.setRol(usuario.getRol());
            usuarioEx.setEstilo(usuario.getEstilo());

            return usuarioRepository.save(usuarioEx);
        } else {
        return null;
        }
    } 

    public Usuario actualizarUsuarioParcial(Long id, Usuario usuario){
        Usuario usuarioEx = usuarioRepository.findById(id).orElse(usuario);
        if (usuarioEx != null) {
            if(usuario.getNombre() != null){
                usuarioEx.setNombre(usuario.getNombre());
            }
            if(usuario.getApaterno() != null){
                usuarioEx.setApaterno(usuario.getApaterno());
            }
            if(usuario.getUsuario() != null){
                usuarioEx.setUsuario(usuario.getUsuario());            
            }
            if(usuario.getContraseña() != null){
                usuarioEx.setContraseña(usuario.getContraseña());           
            }
            if(usuario.getDireccion() != null){
                usuarioEx.setDireccion(usuario.getDireccion());        
            }
            if(usuario.getTelefono() != null){
                usuarioEx.setTelefono(usuario.getTelefono());       
            }
            if(usuario.getRol() != null){
                usuarioEx.setRol(usuario.getRol());      
            }
            if(usuario.getEstilo() != null){
                usuarioEx.setEstilo(usuario.getEstilo());          
            }

            return usuarioRepository.save(usuarioEx);
            }else{
            return null;
        }
    }
}
