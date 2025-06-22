package com.ribda_PopShoes.cl.popShoes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ribda_PopShoes.cl.popShoes.model.Color;
import com.ribda_PopShoes.cl.popShoes.model.Estilo;
import com.ribda_PopShoes.cl.popShoes.repository.ColorRepository;
import com.ribda_PopShoes.cl.popShoes.repository.EstiloRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ColorService {
    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private EstiloRepository estiloRepository;

    public List<Color> obtenerColores(){
        return colorRepository.findAll();
    }

    public Color obtenerColorPorId(Long id){
        return colorRepository.findById(id).orElse(null);
    }

    public Color guardarColor(Color color){
        return colorRepository.save(color);
    }

    public void eliminarColor(Long id){
    Color color = colorRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Color no encontrado"));

    List<Estilo> estilos = new ArrayList<>(color.getEstilos());
    for (Estilo estilo : estilos) {
        estilo.getColores().remove(color);
        estiloRepository.save(estilo);
    }

    color.getEstilos().clear();
    colorRepository.save(color);
    colorRepository.delete(color);
}

    public Color actualizarColor(Long id, Color color){
        Color colorEx = colorRepository.findById(id).orElse(null);
        if (colorEx != null){
            colorEx.setNombre(color.getNombre());
            return colorRepository.save(colorEx);
        }else{return null;}
    }


}
