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

import com.ribda_PopShoes.cl.popShoes.model.Color;
import com.ribda_PopShoes.cl.popShoes.model.Estilo;
import com.ribda_PopShoes.cl.popShoes.model.Influencer;
import com.ribda_PopShoes.cl.popShoes.repository.EstiloRepository;
import com.ribda_PopShoes.cl.popShoes.service.EstiloService;

@SpringBootTest
public class EstiloServiceTest {

    @Autowired
    private EstiloService estiloService;

    @MockBean
    private EstiloRepository estiloRepository;

    private Estilo crearEstilo(){
        Influencer influencer = new Influencer();
        influencer.setId(1);
        influencer.setNombre("Bad bunny");
        influencer.setDescripcion("Cantante de puerto rico");
        List<Influencer> influencers = new ArrayList<>();
        influencers.add(influencer);

        Color color = new Color();
        color.setId(1);
        color.setNombre("Amarillo");
        List<Color> colores = new ArrayList<>();
        colores.add(color);

        return new Estilo(1, "casual", "Un estilo casual", influencers, colores);
    }

    @Test
    public void testFindAll(){
        when(estiloRepository.findAll()).thenReturn(List.of(crearEstilo()));
        List<Estilo> estilos = estiloService.obtenerEstilos();
        assertNotNull(estilos);
        assertEquals(1, estilos.size());
    }

    @Test
    public void testFindById(){
        when(estiloRepository.findById(1L)).thenReturn(Optional.of(crearEstilo()));
        Estilo estilo = estiloService.obtenerEstiloPorId(1L);
        assertNotNull(estilo);
        assertEquals("casual", estilo.getNombre());
    }

    @Test
    public void testSave(){
        Estilo estilo = crearEstilo();
        when(estiloRepository.save(estilo)).thenReturn(estilo);
        Estilo savedEstilo = estiloService.guardarEstilo(estilo);
        assertNotNull(savedEstilo);
        assertEquals("casual", savedEstilo.getNombre());
    }

    @Test
    public void testPatchEstilo(){
        Estilo existingEstilo = crearEstilo();
        Estilo patchData = new Estilo();
        patchData.setNombre("elegante");
        patchData.setDescripcion("Un estilo elegante");
        
        when(estiloRepository.findById(1L)).thenReturn(Optional.of(existingEstilo));
        when(estiloRepository.save(any(Estilo.class))).thenReturn(existingEstilo);
        
        Estilo patchedEstilo = estiloService.actualizarEstiloParcial(1L, patchData);
        assertNotNull(patchedEstilo);
        assertEquals("elegante", patchedEstilo.getNombre());
    }

    @Test
    public void testDeleteById(){
        doNothing().when(estiloRepository).deleteById(1L);
        estiloService.eliminarEstilo(1L);
        verify(estiloRepository, times(1)).deleteById(1L);
    }

        @Test
    public void testFindByInfluencersId(){
        Long influencerId = 1L;
        when(estiloRepository.findByInfluencers_Id(influencerId)).thenReturn(List.of(crearEstilo()));
        List<Estilo> estilos = estiloService.obtenerEstilosPorInfluencerId(influencerId);
        assertNotNull(estilos);
        assertEquals(1, estilos.size());
        assertEquals("casual", estilos.get(0).getNombre());
        verify(estiloRepository, times(1)).findByInfluencers_Id(influencerId);
    }

    @Test
    public void testFindByColoresId(){
        Long colorId = 1L;
        when(estiloRepository.findByColores_Id(colorId)).thenReturn(List.of(crearEstilo()));
        List<Estilo> estilos = estiloService.obtenerEstilosPorColorId(colorId);
        assertNotNull(estilos);
        assertEquals(1, estilos.size());
        assertEquals("casual", estilos.get(0).getNombre());
        verify(estiloRepository, times(1)).findByColores_Id(colorId);
    }

    @Test
    public void testFindByInfluencersIdAndColoresId(){
        Integer idInfluencer = 1;
        Integer idColor = 1;
        when(estiloRepository.findByInfluencersIdAndColoresId(idInfluencer, idColor)).thenReturn(List.of(crearEstilo()));
        List<Estilo> estilos = estiloService.findByInfluencerIdAndColorId(idInfluencer, idColor);
        assertNotNull(estilos);
        assertEquals(1, estilos.size());
        assertEquals("casual", estilos.get(0).getNombre());
        verify(estiloRepository, times(1)).findByInfluencersIdAndColoresId(idInfluencer, idColor);
    }
}
