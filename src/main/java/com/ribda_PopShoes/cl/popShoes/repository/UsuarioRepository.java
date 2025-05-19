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
            u.estilo.nombre, u.rol.nombre, c.nombre 
            FROM Usuario u JOIN u.calzados c
            """)
    List<Object[]> findUsuarioConEstiloRolYCalzados();


}
