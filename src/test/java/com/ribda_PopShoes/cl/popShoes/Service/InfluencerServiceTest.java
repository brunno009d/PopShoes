package com.ribda_PopShoes.cl.popShoes.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ribda_PopShoes.cl.popShoes.model.Color;
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

        return new Influencer(001, "BadBunny", "Cantante", estilos);

    }
}
