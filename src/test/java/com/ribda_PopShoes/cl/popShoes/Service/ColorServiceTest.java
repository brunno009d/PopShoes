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

        return new Color(1, "Amarillo", estilos);
    }

    @Test
    public void testFindAll(){
        when(colorRepository.findAll()).thenReturn(List.of(crearColor()));
        List<Color> colores = colorService.obtenerColores();
        assertNotNull(colores);
        assertEquals(1, colores.size());
    }

    @Test
    public void testFindById(){
        when(colorRepository.findById(1L)).thenReturn(Optional.of(crearColor()));
        Color color = colorService.obtenerColorPorId(1L);
        assertNotNull(color);
        assertEquals("Amarillo", color.getNombre());
    }

    @Test
    public void testSave(){
        Color color = crearColor();
        when(colorRepository.save(color)).thenReturn(color);
        Color savedColor = colorService.guardarColor(color);
        assertNotNull(savedColor);
        assertEquals("Amarillo", savedColor.getNombre());
    }

    @Test
    public void testPatchColor(){
        Color existingColor = crearColor();
        Color patchData = new Color();
        patchData.setNombre("Verde");
        
        when(colorRepository.findById(1L)).thenReturn(Optional.of(existingColor));
        when(colorRepository.save(any(Color.class))).thenReturn(existingColor);
        
        Color patchedColor = colorService.actualizarColor(1L, patchData);
        assertNotNull(patchedColor);
        assertEquals("Verde", patchedColor.getNombre());
    }

    @Test
    public void testDeleteById(){
        doNothing().when(colorRepository).deleteById(1L);
        colorService.eliminarColor(1L);
        verify(colorRepository, times(1)).deleteById(1L);
    }
}
