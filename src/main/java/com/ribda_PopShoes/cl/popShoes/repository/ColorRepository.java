package com.ribda_PopShoes.cl.popShoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ribda_PopShoes.cl.popShoes.model.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
    @Query("""
            SELECT c, e.nombre FROM Color c JOIN c.estilos e
            """)

    List<Object[]> findColorConEstilo();

    List<Color> findByEstilos_Id(Long estiloId);

}
