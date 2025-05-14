package com.ribda_PopShoes.cl.popShoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ribda_PopShoes.cl.popShoes.model.Color;
import com.ribda_PopShoes.cl.popShoes.repository.ColorRepository;

@Service
public class ColorService {
    @Autowired
    private ColorRepository colorRepository;

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
        colorRepository.deleteById(id);
    }

    public Color actualizarColor(Long id, Color color){
        Color colorEx = colorRepository.findById(id).orElse(null);
        if (colorEx != null){
            colorEx.setNombre(color.getNombre());
            return colorRepository.save(colorEx);
        }else{return null;}
    }


}
