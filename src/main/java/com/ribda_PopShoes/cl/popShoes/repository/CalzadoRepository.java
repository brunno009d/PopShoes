package com.ribda_PopShoes.cl.popShoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ribda_PopShoes.cl.popShoes.model.Calzado;
import com.ribda_PopShoes.cl.popShoes.model.Estilo;
import com.ribda_PopShoes.cl.popShoes.model.Usuario;

@Repository
public interface CalzadoRepository extends JpaRepository<Calzado ,Long> {

    @Query("""
            SELECT c.id, c.nombre, c.estilo.nombre, c.categoria.nombre, c.marca.nombre, COUNT(u.id)
            FROM Calzado c 
            LEFT JOIN c.usuarios u
            GROUP BY c.nombre, c.estilo.nombre, c.categoria.nombre, c.marca.nombre
            ORDER BY c.id

            """)
    List<Object[]> findCalzadoConMarcaYEstiloYCategoria();

    @Query("""
            SELECT c.id, c.nombre, c.talla ,c.marca.nombre, e.nombre, co.nombre
            FROM Calzado c
            JOIN c.estilo e
            JOIN e.colores co
            ORDER BY c.id
            
            """)
    List<Object[]> findCalzadoConColor();

    @Query("""
            SELECT c.id, c.nombre, c.talla ,c.marca.nombre, e.nombre, i.nombre
            FROM Calzado c
            JOIN c.estilo e
            JOIN e.influencers i
            ORDER BY c.id
            
            """)
    List<Object[]> findCalzadoConInfluencer();

    List<Calzado> findByNombreAndTalla(String nombre, Integer numero);
    List<Calzado> findByCategoriaIdAndMarcaId(Integer idCategoria, Integer idMarca);
    List<Calzado> findByEstiloIdAndCategoriaId(Integer idEstilo, Integer idCategoria);

    List<Calzado> findByUsuarios_Id(Long usuarioId);
    List<Calzado> findByEstilo_Id(Long estiloId);
    
    void deleteByEstilo(Estilo estilo);


}
