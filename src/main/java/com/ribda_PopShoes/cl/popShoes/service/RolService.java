package com.ribda_PopShoes.cl.popShoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ribda_PopShoes.cl.popShoes.model.Rol;
import com.ribda_PopShoes.cl.popShoes.repository.RolRepository;;

@Service
public class RolService {
    @Autowired
    private RolRepository rolRepository;

    public List<Rol> obtenerRoles(){
        return rolRepository.findAll();
    }

    public Rol obtenerRolPorId(Long id){
        return rolRepository.findById(id).orElse(null);
    }

    public Rol guardarRol(Rol rol){
        return rolRepository.save(rol);
    }

    public void eliminarRol(Long id){
        rolRepository.deleteById(id);
    }

    public Rol actualizarRol(Long id, Rol rol){
        Rol rolEx = rolRepository.findById(id).orElse(null);
        if(rolEx != null){
            rolEx.setNombre(rol.getNombre());
            return rolRepository.save(rolEx);
        }else{ return null;}
    } 

}
