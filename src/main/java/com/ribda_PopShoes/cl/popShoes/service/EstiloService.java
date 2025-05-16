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

    public List<Map<String, Object>> obtenerEstiloConNombres() {
        List<Object[]> resultados = estiloRepository.findEstiloConInfluencerYColor();
        List<Map<String, Object>> lista = new ArrayList<>();

        for (Object[] fila : resultados) {
            Map<String, Object> datos = new HashMap<>();
            datos.put("estilo", fila[0]);
            datos.put("influencer", fila[1]);
            datos.put("color", fila[2]);
            lista.add(datos);
        }

        return lista;
    }

    public List<Estilo> obtenerEstiloPorInfluencerId(Long influencerId) {
        return estiloRepository.findByInfluencerId(influencerId);
    }
    public List<Estilo> obtenerEstiloPorColorId(Long colorId) {
        return estiloRepository.findByColorId(colorId);
    }
    public List<Estilo> obtenerEstilosPorInfluencerIdYColorId(Long influencerId, Long colorId) {
        return estiloRepository.findByInfluencerId(influencerId).stream()
                .filter(estilo -> estilo.getColor().getId().equals(colorId))
                .collect(Collectors.toList());
    }
    public List<Estilo> obtenerEstilosPorRolIdYEstiloId(Long influencerId, Long colorId) {
        return estiloRepository.findByColorId(colorId).stream()
                .filter(estilo -> estilo.getInfluencer().getId().equals(influencerId))
                .collect(Collectors.toList());
    }

}
