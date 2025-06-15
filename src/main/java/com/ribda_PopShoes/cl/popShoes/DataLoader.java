package com.ribda_PopShoes.cl.popShoes;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

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
            

        }

    }


    
}
