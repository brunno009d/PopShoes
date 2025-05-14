package com.ribda_PopShoes.cl.popShoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ribda_PopShoes.cl.popShoes.model.Estilo;
import com.ribda_PopShoes.cl.popShoes.repository.EstiloRepository;

@Service
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
            estiloEx.setInfluencer(estilo.getInfluencer());
            estiloEx.setColor(estilo.getColor());
            return estiloRepository.save(estiloEx);
        }else{return null;}
    }

    public Estilo actualizarEstiloParcial(Long id, Estilo estilo){
        Estilo estiloEx = estiloRepository.findById(id).orElse(null);
        if(estiloEx != null){
            if(estiloEx.getNombre() != null){
                estiloEx.setNombre(estilo.getNombre());
            }
            if(estiloEx.getDescripcion() != null){
                estiloEx.setDescripcion(estilo.getDescripcion());
            }
            if(estiloEx.getInfluencer() != null){
                estiloEx.setInfluencer(estilo.getInfluencer());
            }
            if(estiloEx.getColor() != null){
                estiloEx.setColor(estilo.getColor());
            }
            return estiloRepository.save(estiloEx);
        }else{return null;}
    }
}
