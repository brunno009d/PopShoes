package com.ribda_PopShoes.cl.popShoes.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ribda_PopShoes.cl.popShoes.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

import com.ribda_PopShoes.cl.popShoes.model.Usuario;;;

@Service
@Transactional
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
            Map<String, Object> datos = new LinkedHashMap<>();
            datos.put("id", fila[0]);
            datos.put("usuario", fila[1]);
            datos.put("nombre", fila[2]);
            datos.put("apaterno", fila[3]);
            datos.put("amaterno", fila[4]);
            datos.put("direccion", fila[5]);
            datos.put("telefono", fila[6]);
            datos.put("rol", fila[7]);
            datos.put("calzados", fila[8]);
            lista.add(datos);
        }

        return lista;
    }

    public List<Map<String, Object>> obtenerUsuarioConInfluencers(){
        List<Object[]> resultados = usuarioRepository.findUsuarioConInfluencer();
        List<Map<String, Object>> lista = new ArrayList<>();

        for (Object[] fila : resultados){
            Map<String, Object> datos = new LinkedHashMap<>();
            datos.put("id_usuario", fila[0]);
            datos.put("nombre_usuario", fila[1]);
            datos.put("apaterno", fila[2]);
            datos.put("amaterno", fila[3]);
            datos.put("estilo", fila[4]);
            datos.put("influencer", fila[5]);
            lista.add(datos);
        }
        return lista;
    }

    public List<Map<String, Object>> obtenerUsuarioConColores(){
        List<Object[]> resultados = usuarioRepository.findUsuarioConColor();
        List<Map<String, Object>> lista = new ArrayList<>();

        for (Object[] fila : resultados){
            Map<String, Object> datos = new LinkedHashMap<>();
            datos.put("id_usuario", fila[0]);
            datos.put("nombre_usuario", fila[1]);
            datos.put("apaterno", fila[2]);
            datos.put("amaterno", fila[3]);
            datos.put("estilo", fila[4]);
            datos.put("color", fila[5]);
            lista.add(datos);
        }
        return lista;
        
    }


}
