package com.ribda_PopShoes.cl.popShoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ribda_PopShoes.cl.popShoes.model.Influencer;

@Repository
public interface InfluencerRepository extends JpaRepository<Influencer, Long> {
    @Query("""
            SELECT i, i.estilo.nombre,  FROM i
            """)
    List<Object[]> findByInfluencerConEstilo();

    List<Influencer> findByEstiloId(Long estiloId);
}
