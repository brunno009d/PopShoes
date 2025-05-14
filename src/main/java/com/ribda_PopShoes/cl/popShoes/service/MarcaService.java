package com.ribda_PopShoes.cl.popShoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ribda_PopShoes.cl.popShoes.model.Marca;
import com.ribda_PopShoes.cl.popShoes.repository.MarcaRepository;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public List<Marca> obtenerMarcas(){
        return marcaRepository.findAll();
    }

    public Marca obtenerMarcaPorId(Long id){
        return marcaRepository.findById(id).orElse(null);
    }

    public Marca guardarMarca(Marca marca){
        return marcaRepository.save(marca);
    }

    public void eliminarMarca(Long id){
        marcaRepository.deleteById(id);
    }

    public Marca actualizarMarca(Long id, Marca marca){
        Marca marcaEx = marcaRepository.findById(id).orElse(null);
        if(marcaEx != null){
            marcaEx.setNombre(marca.getNombre());
            return marcaRepository.save(marcaEx);
        }else{return null;}
    }
}
