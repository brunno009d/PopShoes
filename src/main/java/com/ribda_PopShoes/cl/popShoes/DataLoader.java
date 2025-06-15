package com.ribda_PopShoes.cl.popShoes;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.ribda_PopShoes.cl.popShoes.model.Categoria;
import com.ribda_PopShoes.cl.popShoes.model.Color;
import com.ribda_PopShoes.cl.popShoes.model.Estilo;
import com.ribda_PopShoes.cl.popShoes.model.Influencer;
import com.ribda_PopShoes.cl.popShoes.model.Marca;
import com.ribda_PopShoes.cl.popShoes.model.Rol;
import com.ribda_PopShoes.cl.popShoes.repository.CalzadoRepository;
import com.ribda_PopShoes.cl.popShoes.repository.CategoriaRepository;
import com.ribda_PopShoes.cl.popShoes.repository.ColorRepository;
import com.ribda_PopShoes.cl.popShoes.repository.EstiloRepository;
import com.ribda_PopShoes.cl.popShoes.repository.InfluencerRepository;
import com.ribda_PopShoes.cl.popShoes.repository.MarcaRepository;
import com.ribda_PopShoes.cl.popShoes.repository.RolRepository;
import com.ribda_PopShoes.cl.popShoes.repository.UsuarioRepository;

import net.datafaker.Faker;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    private CalzadoRepository calzadoRepository;

    @Autowired 
    private ColorRepository colorRepository;

    @Autowired
    private EstiloRepository estiloRepository;

    @Autowired 
    private InfluencerRepository influencerRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    private MarcaRepository marcaRepository;

    @Override
    public void run(String... args)throws Exception{
        Faker faker = new Faker();
        Random random = new Random();
        
        for(int i = 0; i < 3; i++){
            Rol rol = new Rol();
            rol.setId(i + 1);
            rol.setNombre(faker.name().firstName());
            rolRepository.save(rol);
        }

        for(int i = 0; i <3; i++){
            Color color = new Color();
            color.setId(i + 1);
            color.setNombre(faker.color().name());
            colorRepository.save(color);
        }

        for(int i = 0; i < 3; i ++){
            Influencer influencer = new Influencer();
            influencer.setId(i + 1);
            influencer.setNombre(faker.name().fullName());
            influencer.setDescripcion(faker.company().catchPhrase());
            influencerRepository.save(influencer);
        }

        for(int i = 0; i < 3; i++){
            Estilo estilo = new Estilo();
            estilo.setId(i + 1);
            estilo.setNombre(faker.options().option("Casual", "Informal", "Deportivo"));
            estilo.setDescripcion(faker.lorem().sentence());
            estiloRepository.save(estilo);
        }

        List<Color> colores = colorRepository.findAll();
        List<Influencer> influencers = influencerRepository.findAll();
        List<Estilo> estilos = estiloRepository.findAll();

        for(Estilo estilo : estilos){

        }


        // para estilos color y estilos influencer


        for(int i = 0; i < 3; i++){
            Categoria categoria = new Categoria();
            categoria.setId(i + 1);
            categoria.setNombre(faker.commerce().department());
            categoriaRepository.save(categoria);
        }

        for(int i = 0; i < 3; i++){
            Marca marca = new Marca();
            marca.setId(i + 1);
            marca.setNombre(faker.company().name());
            marcaRepository.save(marca);
        }  

        // calzado

        //usuario
        for (int i = 0; i < 3; i ++);



    }


    
}
