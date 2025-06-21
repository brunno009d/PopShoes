package com.ribda_PopShoes.cl.popShoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ribda_PopShoes.cl.popShoes.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{    
    @Query("""
            SELECT u.id, u.usuario, u.nombre, u.apaterno, u.amaterno, u.direccion, u.telefono,
            u.rol.nombre, c.nombre 
            FROM Usuario u JOIN u.calzados c
            """)
    List<Object[]> findUsuarioConEstiloRolYCalzados();

    @Query("""
            SELECT u.id, u.nombre, u.apaterno, u.amaterno, e.nombre, i.nombre
            FROM Usuario u
            JOIN u.calzados c
            JOIN c.estilo e
            JOIN e.influencers i
            ORDER BY u.id

            """)
    List<Object[]> findUsuarioConInfluencer();

    @Query("""
            SELECT u.id, u.nombre, u.apaterno, u.amaterno, e.nombre, co.nombre
            FROM Usuario u
            JOIN u.calzados c
            JOIN c.estilo e
            JOIN e.colores co
            ORDER BY u.id

            """)
    List<Object[]> findUsuarioConColor();


}
