package com.ribda_PopShoes.cl.popShoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ribda_PopShoes.cl.popShoes.model.Estilo;

@Repository
public interface EstiloRepository extends JpaRepository<Estilo, Long>{
    @Query("""
            SELECT e, e.nombre, c.nombre FROM Estilo e JOIN e.influencers i JOIN e.colores c
            """)

    List<Object[]> findEstiloConInfluencerYColor();

    List<Estilo> findByInfluencers_Id(Long influencerId);
    List<Estilo> findByColores_Id(Long colorId);
}
