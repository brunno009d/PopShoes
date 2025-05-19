package com.ribda_PopShoes.cl.popShoes.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Calzado actualizarCalzadoParcial(Long id, Calzado calzado) {
    Calzado calzadoEx = calzadoRepository.findById(id).orElse(null);
    if (calzadoEx != null) {
        if (calzado.getNombre() != null) {
            calzadoEx.setNombre(calzado.getNombre());
        }
        if (calzado.getTalla() != null) {
            calzadoEx.setTalla(calzado.getTalla());
        }
        if (calzado.getEstilo() != null) {
            calzadoEx.setEstilo(calzado.getEstilo());
        }
        if (calzado.getCategoria() != null) {
            calzadoEx.setCategoria(calzado.getCategoria());
        }
        if (calzado.getMarca() != null) {
            calzadoEx.setMarca(calzado.getMarca());
        }
        return calzadoRepository.save(calzadoEx);
        } else {
        return null;}
    }

    public List<Map<String, Object>> obtenerCalzadosConNombres(){
        List<Object[]> resultados = calzadoRepository.findCalzadoConMarcaYEstiloYCategoria();
        List<Map<String, Object>> lista = new ArrayList<>();

        for (Object[] fila : resultados) {
            Map<String, Object> datos = new HashMap<>();
            datos.put("calzado", fila[0]);
            datos.put("nombreMarca", fila[1]);
            datos.put("nombreCategoria", fila[2]);
            datos.put("nombreEstilo", fila[3]);
            lista.add(datos);

        }
        return lista;
    }

}
