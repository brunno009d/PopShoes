package com.ribda_PopShoes.cl.popShoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ribda_PopShoes.cl.popShoes.model.Calzado;

@Repository
public interface CalzadoRepository extends JpaRepository<Calzado ,Long> {

    @Query("""
            SELECT c, c.estilo.nombre, c.categoria.nombre, c.marca.nombre FROM Calzado c 
            """)
    List<Object[]> findCalzadoConMarcaYEstiloYCategoria();

}
