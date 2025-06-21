package com.ribda_PopShoes.cl.popShoes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.ribda_PopShoes.cl.popShoes.model.Calzado;
import com.ribda_PopShoes.cl.popShoes.model.Categoria;
import com.ribda_PopShoes.cl.popShoes.model.Color;
import com.ribda_PopShoes.cl.popShoes.model.Estilo;
import com.ribda_PopShoes.cl.popShoes.model.Influencer;
import com.ribda_PopShoes.cl.popShoes.model.Marca;
import com.ribda_PopShoes.cl.popShoes.model.Rol;
import com.ribda_PopShoes.cl.popShoes.model.Usuario;
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

    @Autowired
    private MarcaRepository marcaRepository;

    Faker faker = new Faker();
    Random random = new Random();

    @Override
    public void run(String... args)throws Exception{
       
        for(int i = 0; i < 3; i++){
            Rol rol = new Rol();
            rol.setId(i + 1);
            rol.setNombre(faker.job().position());
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

        // para estilos color y estilos influencer


        List<Color> colores = colorRepository.findAll();
        List<Influencer> influencers = influencerRepository.findAll();

        List<String> nombresEstilos = new ArrayList<>(List.of("Casual", "Informal", "Deportivo", "Formal", "Urbano"));

        for(int i = 0; i < 3; i++){
            Estilo estilo = new Estilo();
            estilo.setId(i + 1);
            estilo.setNombre(nombresEstilos.get(i));
            estilo.setDescripcion(faker.lorem().sentence());
            estilo.setColores(getRandomSubset(colores, 2));
            estilo.setInfluencers(getRandomSubset(influencers, 2));
            estiloRepository.save(estilo);
        }

        List<String> nombresCategorias = new ArrayList<>(List.of("Hombre", "Mujer", "Unisex"));
        for(int i = 0; i < 3; i++){
            Categoria categoria = new Categoria();
            categoria.setId(i + 1);
            categoria.setNombre(nombresCategorias.get(i));
            categoriaRepository.save(categoria);
        }

        for(int i = 0; i < 3; i++){
            Marca marca = new Marca();
            marca.setId(i + 1);
            marca.setNombre(faker.company().name());
            marcaRepository.save(marca);
        }  

        List<Categoria> categorias = categoriaRepository.findAll();
        List<Marca> marcas = marcaRepository.findAll();
        List<Estilo> estilos = estiloRepository.findAll();

        // calzado
        List<String> nombresCalzados = new ArrayList<>(List.of("Air Force 1", "Seude XL", "Old Skool", "Forum"));


        for(int i = 0; i < 4; i++){
        Calzado calzado = new Calzado();
        calzado.setId(i + 1);
        calzado.setNombre(nombresCalzados.get(i));
        calzado.setTalla(faker.number().numberBetween(36, 45));
        calzado.setEstilo(estilos.get(random.nextInt(estilos.size())));
        calzado.setCategoria(categorias.get(random.nextInt(categorias.size())));
        calzado.setMarca(marcas.get(random.nextInt(marcas.size())));
        calzadoRepository.save(calzado);
        }

        //usuario
        List<Rol> roles = rolRepository.findAll();
        List<Calzado> calzados = calzadoRepository.findAll();


        for (int i = 0; i < 3; i ++){
            Usuario usuario = new Usuario();
            usuario.setId(i + 1);
            usuario.setNombre(faker.name().firstName());
            usuario.setApaterno(faker.name().lastName());
            usuario.setAmaterno(faker.name().lastName());
            usuario.setUsuario(faker.internet().username());
            usuario.setContraseña(faker.internet().password());
            usuario.setDireccion(faker.address().fullAddress());
            usuario.setTelefono(faker.number().numberBetween(100000000, 999999999));
            usuario.setRol(roles.get(random.nextInt(roles.size())));
            usuario.setCalzados(getRandomSubset(calzados, 2));
            usuarioRepository.save(usuario);
        }
    }

    private <T> List<T> getRandomSubset(List<T> lista, int cantidad) {
        Set<T> resultado = new HashSet<>();
        while (resultado.size() < cantidad && resultado.size() < lista.size()) {
            resultado.add(lista.get(random.nextInt(lista.size())));
        }
        return List.copyOf(resultado);
    }


    
}
