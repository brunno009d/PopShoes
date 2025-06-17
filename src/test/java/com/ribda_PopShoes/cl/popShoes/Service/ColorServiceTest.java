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

import com.ribda_PopShoes.cl.popShoes.model.Color;
import com.ribda_PopShoes.cl.popShoes.model.Estilo;
import com.ribda_PopShoes.cl.popShoes.repository.ColorRepository;
import com.ribda_PopShoes.cl.popShoes.service.ColorService;

@SpringBootTest
public class ColorServiceTest {
    @Autowired
    private ColorService colorService;

    @MockBean
    private ColorRepository colorRepository;

    private Color crearColor(){
        Estilo estilo = new Estilo();
        estilo.setId(1);
        estilo.setNombre("casual");
        estilo.setDescripcion("un estilo casual");
        List<Estilo> estilos = new ArrayList<>();
        estilos.add(estilo);


        return new Color(001, "Amarillo", estilos);
    }

    @Test
    public void testFindAll(){
        when(colorRepository.findAll()).thenReturn(List.of(crearColor()));
        List<Color> colores = colorService.obtenerColores();
        assertNotNull(colores);
        assertEquals(1, colores.size());
    }


}
