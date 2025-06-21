package com.ribda_PopShoes.cl.popShoes.Service;

import static org.junit.jupiter.api.Assertions.*;
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
import com.ribda_PopShoes.cl.popShoes.repository.UsuarioRepository;
import com.ribda_PopShoes.cl.popShoes.service.UsuarioService;

@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @MockBean
    private UsuarioRepository usuarioRepository;

    private Usuario crearUsuario(){
        Calzado calzado = new Calzado();
        calzado.setId(1);
        calzado.setNombre("Air Force 1");
        calzado.setTalla(40);
        calzado.setCategoria(new Categoria());
        calzado.setMarca(new Marca());
        calzado.setEstilo(new Estilo());
        List<Calzado> calzados = new ArrayList<>();
        calzados.add(calzado);

        return new Usuario(1, "pepito", "Rojas", "Soto", "pepitolol", "123pep", "Avenida Pepito", 123456789, new Rol(), calzados);
    }

    @Test
    public void testFindAll(){
        when(usuarioRepository.findAll()).thenReturn(List.of(crearUsuario()));
        List<Usuario> usuarios = usuarioService.obtenerUsuarios();
        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
    }

    @Test
    public void testFindById(){
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(crearUsuario()));
        Usuario usuario = usuarioService.obtenerUsuarioPorId(1L);
        assertNotNull(usuario);
        assertEquals("pepito", usuario.getNombre());
    }

    @Test
    public void testSave(){
        Usuario usuario = crearUsuario();
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Usuario savedUsuario = usuarioService.guardarUsuario(usuario);
        assertNotNull(savedUsuario);
        assertEquals("pepito", savedUsuario.getNombre());
    }

    @Test
    public void testPatchUsuario(){
        Usuario existingUsuario = crearUsuario();
        Usuario patchData = new Usuario();
        patchData.setNombre("Juan Carlos");
        patchData.setUsuario("juancarlos123");
        
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(existingUsuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(existingUsuario);
        
        Usuario patchedUsuario = usuarioService.actualizarUsuarioParcial(1L, patchData);
        assertNotNull(patchedUsuario);
        assertEquals("Juan Carlos", patchedUsuario.getNombre());
    }

    @Test
    public void testDeleteById(){
        doNothing().when(usuarioRepository).deleteById(1L);
        usuarioService.eliminarUsuario(1L);
        verify(usuarioRepository, times(1)).deleteById(1L);
    }
}