package com.ribda_PopShoes.cl.popShoes.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ribda_PopShoes.cl.popShoes.model.Estilo;
import com.ribda_PopShoes.cl.popShoes.repository.EstiloRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EstiloService {
    @Autowired
    private EstiloRepository estiloRepository;

    public List<Estilo> obtenerEstilos(){
        return estiloRepository.findAll();
    }

    public Estilo obtenerEstiloPorId(Long id){
        return estiloRepository.findById(id).orElse(null);
    }

    public Estilo guardarEstilo(Estilo estilo){
        return estiloRepository.save(estilo);
    }

    public void ElminarEstilo(Long id){
        estiloRepository.deleteById(id);
    }

    public Estilo actualizarEstilo(Long id, Estilo estilo){
        Estilo estiloEx = estiloRepository.findById(id).orElse(null);
        if(estiloEx != null){
            estiloEx.setNombre(estilo.getNombre());
            estiloEx.setDescripcion(estilo.getDescripcion());
            estiloEx.setInfluencers(estilo.getInfluencers());
            estiloEx.setColores(estilo.getColores());
            return estiloRepository.save(estiloEx);
        }else{return null;}
    }

    public Estilo actualizarEstiloParcial(Long id, Estilo estilo){
        Estilo estiloEx = estiloRepository.findById(id).orElse(null);
        if(estiloEx != null){
            if(estilo.getNombre() != null){
                estiloEx.setNombre(estilo.getNombre());
            }
            if(estilo.getDescripcion() != null){
                estiloEx.setDescripcion(estilo.getDescripcion());
            }
            if(estilo.getInfluencers() != null){
                estiloEx.setInfluencers(estilo.getInfluencers());
            }
            if(estilo.getColores() != null){
                estiloEx.setColores(estilo.getColores());
            }
            return estiloRepository.save(estiloEx);
        }else{return null;}
    }

    public List<Estilo> obtenerEstilosPorInfluencerId(long influencerId){
        return estiloRepository.findByInfluencers_Id(influencerId);

    }

    public List<Estilo> obtenerEstilosPorColorId(long colorId){
        return estiloRepository.findByColores_Id(colorId);
    }

    public List<Estilo> findByInfluencerIdAndColorId(Integer idInfluencer, Integer idColor){
        return estiloRepository.findByInfluencersIdAndColoresId(idInfluencer, idInfluencer);
    }
}
