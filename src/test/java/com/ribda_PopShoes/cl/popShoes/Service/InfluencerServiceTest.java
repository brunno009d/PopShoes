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

import com.ribda_PopShoes.cl.popShoes.model.Estilo;
import com.ribda_PopShoes.cl.popShoes.model.Influencer;
import com.ribda_PopShoes.cl.popShoes.repository.InfluencerRepository;
import com.ribda_PopShoes.cl.popShoes.service.InfluencerService;

@SpringBootTest
public class InfluencerServiceTest {
    @Autowired
    private InfluencerService influencerService;

    @MockBean 
    private InfluencerRepository influencerRepository;

    private Influencer crearInfluencer(){
        Estilo estilo = new Estilo();
        estilo.setId(1);
        estilo.setNombre("casual");
        estilo.setDescripcion("un estilo casual");
        List<Estilo> estilos = new ArrayList<>();
        estilos.add(estilo);

        return new Influencer(1, "BadBunny", "Cantante", estilos);
    }

    @Test
    public void testFindAll(){
        when(influencerRepository.findAll()).thenReturn(List.of(crearInfluencer()));
        List<Influencer> influencers = influencerService.obtenerInfluencers();
        assertNotNull(influencers);
        assertEquals(1, influencers.size());
    }

    @Test
    public void testFindById(){
        when(influencerRepository.findById(1L)).thenReturn(Optional.of(crearInfluencer()));
        Influencer influencer = influencerService.obtenerInfluencerPorId(1L);
        assertNotNull(influencer);
        assertEquals("BadBunny", influencer.getNombre());
    }

    @Test
    public void testSave(){
        Influencer influencer = crearInfluencer();
        when(influencerRepository.save(influencer)).thenReturn(influencer);
        Influencer savedInfluencer = influencerService.guardarInfluencer(influencer);
        assertNotNull(savedInfluencer);
        assertEquals("BadBunny", savedInfluencer.getNombre());
    }

    @Test
    public void testPatchInfluencer(){
        Influencer existingInfluencer = crearInfluencer();
        Influencer patchData = new Influencer();
        patchData.setNombre("J Balvin");
        patchData.setDescripcion("Cantante colombiano");
        
        when(influencerRepository.findById(1L)).thenReturn(Optional.of(existingInfluencer));
        when(influencerRepository.save(any(Influencer.class))).thenReturn(existingInfluencer);
        
        Influencer patchedInfluencer = influencerService.actualizarInfluencerParcial(1L, patchData);
        assertNotNull(patchedInfluencer);
        assertEquals("J Balvin", patchedInfluencer.getNombre());
    }

    @Test
    public void testDeleteById(){
        doNothing().when(influencerRepository).deleteById(1L);
        influencerService.eliminarInfluencer(1L);
        verify(influencerRepository, times(1)).deleteById(1L);
    }
}
