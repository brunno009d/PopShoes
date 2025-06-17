package com.ribda_PopShoes.cl.popShoes.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ribda_PopShoes.cl.popShoes.model.Calzado;
import com.ribda_PopShoes.cl.popShoes.model.Categoria;
import com.ribda_PopShoes.cl.popShoes.model.Estilo;
import com.ribda_PopShoes.cl.popShoes.model.Marca;
import com.ribda_PopShoes.cl.popShoes.model.Rol;
import com.ribda_PopShoes.cl.popShoes.model.Usuario;
import com.ribda_PopShoes.cl.popShoes.repository.CalzadoRepository;
import com.ribda_PopShoes.cl.popShoes.service.CalzadoService;

@SpringBootTest
public class CalzadoServiceTest {
    @Autowired
    private CalzadoService calzadoService;

    @MockBean
    private CalzadoRepository calzadoRepository;

    private Calzado crearCalzado(){
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("Juan");
        usuario.setApaterno("rojas");
        usuario.setAmaterno("soto");
        usuario.setUsuario("Juanito123");
        usuario.setContraseña("123ju1");
        usuario.setDireccion("Av123");;
        usuario.setTelefono(123456789);
        usuario.setRol(new Rol());
        usuario.setEstilo(new Estilo());
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario);

        return new Calzado(001,"Air Force 1",40,new Estilo(),new Categoria(),new Marca(), usuarios);
    }

    @Test
    public void testFindAll(){
        when(calzadoRepository.findAll()).thenReturn(List.of(crearCalzado()));
        List<Calzado> calzados = calzadoService.obtenerCalzados();
        assertNotNull(calzados);
        assertEquals(1, calzados.size());
    }
}
