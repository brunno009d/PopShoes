package com.ribda_PopShoes.cl.popShoes.Service;

import java.util.ArrayList;
import java.util.List;

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

    public Usuario crearUsuario(){
        Calzado calzado = new Calzado();
        calzado.setId(1);
        calzado.setNombre("Air Force 1");
        calzado.setTalla(40);
        calzado.setCategoria(new Categoria());
        calzado.setMarca(new Marca());
        calzado.setEstilo(new Estilo());
        List<Calzado> calzados = new ArrayList<>();
        calzados.add(calzado);

        return new Usuario(001, "pepito", "Rojas", "Soto", "pepitolol", "123pep", "Avenida Pepito", 123456789, new Rol(), new Estilo(), calzados);

    }
}
