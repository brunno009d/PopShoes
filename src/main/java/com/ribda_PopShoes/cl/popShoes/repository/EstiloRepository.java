package com.ribda_PopShoes.cl.popShoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ribda_PopShoes.cl.popShoes.model.Estilo;

@Repository
public interface EstiloRepository extends JpaRepository<Estilo, Long>{
    @Query("""
            SELECT e, e.influencer.nombre, e.color.nombre FROM Estilo e
            """)

    List<Object[]> findEstiloConInfluencerYColor();

    List<Estilo> findByInfluencerId(Long influencerId);
    List<Estilo> findByColorId(Long colorId);
}
