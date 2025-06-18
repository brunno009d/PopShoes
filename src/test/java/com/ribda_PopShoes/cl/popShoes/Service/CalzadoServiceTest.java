package com.ribda_PopShoes.cl.popShoes.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        usuario.setDireccion("Av123");
        usuario.setTelefono(123456789);
        usuario.setRol(new Rol());
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario);

        return new Calzado(1, "Air Force 1", 40, new Estilo(), new Categoria(), new Marca(), usuarios);
    }

    @Test
    public void testFindAll(){
        when(calzadoRepository.findAll()).thenReturn(List.of(crearCalzado()));
        List<Calzado> calzados = calzadoService.obtenerCalzados();
        assertNotNull(calzados);
        assertEquals(1, calzados.size());
    }

    @Test
    public void testFindById(){
        when(calzadoRepository.findById(1L)).thenReturn(Optional.of(crearCalzado()));
        Calzado calzado = calzadoService.obtenerCalzadoPorId(1L);
        assertNotNull(calzado);
        assertEquals("Air Force 1", calzado.getNombre());
    }

    @Test
    public void testSave(){
        Calzado calzado = crearCalzado();
        when(calzadoRepository.save(calzado)).thenReturn(calzado);
        Calzado savedCalzado = calzadoService.guardarCalzado(calzado);
        assertNotNull(savedCalzado);
        assertEquals("Air Force 1", savedCalzado.getNombre());
    }

    @Test
    public void testDeleteById(){
        doNothing().when(calzadoRepository).deleteById(1L);
        calzadoService.elminarCalzado(1L);
        verify(calzadoRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testPatchCalzado(){
        Calzado calzadoExistente = crearCalzado();
        Calzado calzadoActualizado = crearCalzado();
        calzadoActualizado.setNombre("Air Force 1 V2");
        calzadoActualizado.setTalla(42);
        
        when(calzadoRepository.findById(1L)).thenReturn(Optional.of(calzadoExistente));
        when(calzadoRepository.save(any(Calzado.class))).thenReturn(calzadoActualizado);
        
        Calzado resultado = calzadoService.actualizarCalzadoParcial(1L, calzadoActualizado);
        
        assertNotNull(resultado);
        assertEquals("Air Force 1 V2", resultado.getNombre());
        assertEquals(42, resultado.getTalla());
        verify(calzadoRepository, times(1)).findById(1L);
        verify(calzadoRepository, times(1)).save(any(Calzado.class));
    }
}
