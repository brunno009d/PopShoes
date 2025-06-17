package com.ribda_PopShoes.cl.popShoes.Service;

import java.util.ArrayList;
import java.util.List;

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

        return new Estilo(1,"casual", "Un estilo casual", influencers, colores);
    }
}
