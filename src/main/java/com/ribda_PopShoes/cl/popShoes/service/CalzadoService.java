package com.ribda_PopShoes.cl.popShoes.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ribda_PopShoes.cl.popShoes.model.Calzado;
import com.ribda_PopShoes.cl.popShoes.repository.CalzadoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
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

        public List<Map<String, Object>> obtenerCalzadosConColores(){
        List<Object[]> resultados = calzadoRepository.findCalzadoConColor();
        List<Map<String, Object>> lista = new ArrayList<>();

        for (Object[] fila : resultados) {
            Map<String, Object> datos = new LinkedHashMap<>();
            datos.put("id_calzado", fila[0]);
            datos.put("nombre_calzado", fila[1]);
            datos.put("talla", fila[2]);
            datos.put("marca", fila[3]);
            datos.put("estilo", fila[4]);
            datos.put("color", fila[5]);
            datos.put("cantidad_usuario", fila[6]);
            lista.add(datos);

        }
        return lista;
    }

        public List<Map<String, Object>> obtenerCalzadosConInfluencer(){
        List<Object[]> resultados = calzadoRepository.findCalzadoConInfluencer();
        List<Map<String, Object>> lista = new ArrayList<>();

        for (Object[] fila : resultados) {
            Map<String, Object> datos = new LinkedHashMap<>();
            datos.put("id_calzado", fila[0]);
            datos.put("nombre_calzado", fila[1]);
            datos.put("talla", fila[2]);
            datos.put("marca", fila[3]);
            datos.put("estilo", fila[4]);
            datos.put("influencer", fila[5]);
            datos.put("cantidad_usuario", fila[6]);
            lista.add(datos);

        }
        return lista;
    }

    public List<Calzado> findByCalzadoNombreAndTalla(String nombre, Integer numero){
        return calzadoRepository.findByNombreAndTalla(nombre, numero);
    }

    public List<Calzado> findByCaregoriaIdAndMarcaId(Integer idCategoria, Integer idMarca){
        return calzadoRepository.findByCategoriaIdAndMarcaId(idCategoria, idMarca);
    }

    public List<Calzado> findByEstiloIdAndCategoriaId(Integer idEstilo, Integer idCategoria){
        return calzadoRepository.findByEstiloIdAndCategoriaId(idEstilo, idCategoria);
    }

}
