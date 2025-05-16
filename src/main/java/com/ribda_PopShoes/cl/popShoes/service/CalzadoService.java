package com.ribda_PopShoes.cl.popShoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ribda_PopShoes.cl.popShoes.model.Calzado;
import com.ribda_PopShoes.cl.popShoes.repository.CalzadoRepository;

@Service
public class CalzadoService {
    @Autowired
    private CalzadoRepository calzadoRepository;

    public List<Calzado> obtenerCalzados(){
        return calzadoRepository.findAll();
    }

    public Calzado obtenerCalzadoPorId(Long id){
        return calzadoRepository.findById(id).orElse(null);
    }

    public Calzado guardarCalzado(Calzado calzado){
        return calzadoRepository.save(calzado);
    }

    public void elminarCalzado(Long id){
        calzadoRepository.deleteById(id);
    }

    public Calzado actualizarCalzado(Long id, Calzado calzado){
        Calzado calzadoEx = calzadoRepository.findById(id).orElse(null);
        if(calzadoEx != null){
            calzadoEx.setNombre(calzado.getNombre());
            calzadoEx.setTalla(calzado.getTalla());
            calzadoEx.setEstilo(calzado.getEstilo());
            calzadoEx.setCategoria(calzado.getCategoria());
            calzadoEx.setMarca(calzado.getMarca());
            return calzadoRepository.save(calzadoEx);
        }else{return null;}
    }

    public Calzado actualizarCalzadoParcial(Long id, Calzado calzado){
        Calzado calzadoEx = calzadoRepository.findById(id).orElse(null);
        if(calzadoEx != null){
            if(calzadoEx.getNombre() != null){
                calzadoEx.setNombre(calzado.getNombre());
            }
            if(calzadoEx.getTalla() != null){
                calzadoEx.setTalla(calzado.getTalla());
            }
            if(calzadoEx.getEstilo() != null){
                calzadoEx.setEstilo(calzado.getEstilo());
            }
            if(calzadoEx.getCategoria() != null){
                calzadoEx.setCategoria(calzado.getCategoria());
            }
            if(calzadoEx.getMarca() != null){
                calzadoEx.setMarca(calzado.getMarca());
            }
            return calzadoRepository.save(calzadoEx);
        }else{return null;}
    }

}
