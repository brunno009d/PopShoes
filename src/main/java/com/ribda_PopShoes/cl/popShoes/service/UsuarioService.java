package com.ribda_PopShoes.cl.popShoes.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

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
            usuarioEx.setAmaterno(usuario.getAmaterno());
            usuarioEx.setUsuario(usuario.getUsuario());
            usuarioEx.setContraseña(usuario.getContraseña());
            usuarioEx.setDireccion(usuario.getDireccion());
            usuarioEx.setTelefono(usuario.getTelefono());
            usuarioEx.setRol(usuario.getRol());
            usuarioEx.setEstilo(usuario.getEstilo());
            usuarioEx.setCalzados(usuario.getCalzados());

            return usuarioRepository.save(usuarioEx);
        } else {
        return null;
        }
    } 

    public Usuario actualizarUsuarioParcial(Long id, Usuario usuario){
        Usuario usuarioEx = usuarioRepository.findById(id).orElse(null);
        if (usuarioEx != null) {
            if(usuario.getNombre() != null){
                usuarioEx.setNombre(usuario.getNombre());
            }
            if(usuario.getApaterno() != null){
                usuarioEx.setApaterno(usuario.getApaterno());
            }
            if(usuario.getAmaterno() != null){
                usuarioEx.setAmaterno(usuario.getAmaterno());
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
            if(usuario.getCalzados() != null){
                usuarioEx.setCalzados(usuario.getCalzados());
            }

            return usuarioRepository.save(usuarioEx);
            }else{
            return null;
        }
    }

     public List<Map<String, Object>> obtenerUsuarioConNombres() {
        List<Object[]> resultados = usuarioRepository.findUsuarioConEstiloRolYCalzados();
        List<Map<String, Object>> lista = new ArrayList<>();

        for (Object[] fila : resultados) {
            Map<String, Object> datos = new HashMap<>();
            datos.put("id", fila[0]);
            datos.put("usuario", fila[1]);
            datos.put("nombre", fila[2]);
            datos.put("apaterno", fila[3]);
            datos.put("amaterno", fila[4]);
            datos.put("direccion", fila[5]);
            datos.put("telefono", fila[6]);
            datos.put("estilo", fila[7]);
            datos.put("rol", fila[8]);
            datos.put("calzados", fila[9]);
            lista.add(datos);
        }

        return lista;
    }

    public List<Usuario> obtenerUsuariosPorEstiloId(Long estiloId) {
        return usuarioRepository.findByEstiloId(estiloId);
    }
    public List<Usuario> obtenerUsuarioPorRolId(Long rolId) {
        return usuarioRepository.findByRolId(rolId);
    }
    public List<Usuario> obtenerUsuariosPorEstiloIdYRolId(Long estiloId, Long rolId) {
        return usuarioRepository.findByEstiloId(estiloId).stream()
                .filter(usuario -> usuario.getRol().getId().equals(rolId))
                .collect(Collectors.toList());
    }
    public List<Usuario> obtenerUsuariosPorRolIdYEstiloId(Long rolId, Long estiloId) {
        return usuarioRepository.findByRolId(rolId).stream()
                .filter(usuario -> usuario.getEstilo().getId().equals(estiloId))
                .collect(Collectors.toList());
    }
}
