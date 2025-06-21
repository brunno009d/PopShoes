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

    @Query("""
            SELECT c.id, c.nombre, c.talla ,c.marca.nombre, e.nombre, co.nombre, COUNT(u.id)
            FROM Calzado c
            left JOIN c.usuarios u
            JOIN c.estilo e
            JOIN e.colores co
            GROUP BY c.id, c.nombre, c.talla ,c.marca.nombre, e.nombre, co.nombre
            ORDER BY c.id
            
            """)
    List<Object[]> findCalzadoConColor();

    @Query("""
            SELECT c.id, c.nombre, c.talla ,c.marca.nombre, e.nombre, i.nombre, COUNT(u.id)
            FROM Calzado c
            left JOIN c.usuarios u
            JOIN c.estilo e
            JOIN e.influencers i
            GROUP BY c.id, c.nombre, c.talla ,c.marca.nombre, e.nombre, i.nombre
            ORDER BY c.id
            
            """)
    List<Object[]> findCalzadoConInfluencer();

    List<Calzado> findByNombreAndTalla(String nombre, Integer numero);
    List<Calzado> findByCategoriaIdAndMarcaId(Integer idCategoria, Integer idMarca);
    List<Calzado> findByEstiloIdAndCategoriaId(Integer idEstilo, Integer idCategoria);


}
