package com.ribda_PopShoes.cl.popShoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ribda_PopShoes.cl.popShoes.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Long>{

}
