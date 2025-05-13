package com.ribda_PopShoes.cl.popShoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ribda_PopShoes.cl.popShoes.model.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

}
