package com.ribda_PopShoes.cl.popShoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ribda_PopShoes.cl.popShoes.model.Categoria;
import com.ribda_PopShoes.cl.popShoes.repository.CategoriaRepository;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> obtenerCategorias(){
        return categoriaRepository.findAll();
    }

    public Categoria obtenercateoriaPorId(Long id){
        return categoriaRepository.findById(id).orElse(null);
    }

    public Categoria guardarCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public void elminarCategoria(Long id){
        categoriaRepository.deleteById(id);
    }

    public Categoria actualizarCategoria(Long id, Categoria categoria){
        Categoria categoriaEx = categoriaRepository.findById(id).orElse(null);
        if(categoriaEx != null){
            categoriaEx.setNombre(categoria.getNombre());
            return categoriaRepository.save(categoriaEx);
        }else{return null;}

    }
}
