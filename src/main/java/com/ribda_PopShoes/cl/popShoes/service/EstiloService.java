package com.ribda_PopShoes.cl.popShoes.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ribda_PopShoes.cl.popShoes.model.Calzado;
import com.ribda_PopShoes.cl.popShoes.model.Color;
import com.ribda_PopShoes.cl.popShoes.model.Estilo;
import com.ribda_PopShoes.cl.popShoes.model.Influencer;
import com.ribda_PopShoes.cl.popShoes.model.Usuario;
import com.ribda_PopShoes.cl.popShoes.repository.CalzadoRepository;
import com.ribda_PopShoes.cl.popShoes.repository.ColorRepository;
import com.ribda_PopShoes.cl.popShoes.repository.EstiloRepository;
import com.ribda_PopShoes.cl.popShoes.repository.InfluencerRepository;
import com.ribda_PopShoes.cl.popShoes.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EstiloService {
    @Autowired
    private EstiloRepository estiloRepository;

    @Autowired
    private CalzadoRepository calzadoRepository;

    @Autowired
    private InfluencerRepository influencerRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Estilo> obtenerEstilos(){
        return estiloRepository.findAll();
    }

    public Estilo obtenerEstiloPorId(Long id){
        return estiloRepository.findById(id).orElse(null);
    }

    public Estilo guardarEstilo(Estilo estilo){
        return estiloRepository.save(estilo);
    }

    public void eliminarEstilo(Long id){
        Estilo estilo = estiloRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Estilo no encontrado"));

        List<Calzado> calzados = calzadoRepository.findByEstilo_Id(id);
        for(Calzado calzado : calzados){
            List<Usuario> usuarios = usuarioRepository.findByCalzados_Id(calzado.getId());
            for (Usuario usuario : usuarios) {
            usuario.getCalzados().remove(calzado);
            usuarioRepository.save(usuario);
        }

        calzado.getUsuarios().clear();
        calzadoRepository.save(calzado);
        calzadoRepository.delete(calzado);

        }

        List<Influencer> influencers = new ArrayList<>(estilo.getInfluencers());
        for (Influencer influencer : influencers) {
            influencer.getEstilos().remove(estilo);
            influencerRepository.save(influencer);
        }

        List<Color> colores = new ArrayList<>(estilo.getColores());
        for (Color color : colores){
            color.getEstilos().remove(estilo);
            colorRepository.save(color);
        }

        estiloRepository.delete(estilo);
    }

    public Estilo actualizarEstilo(Long id, Estilo estilo){
        Estilo estiloEx = estiloRepository.findById(id).orElse(null);
        if(estiloEx != null){
            estiloEx.setNombre(estilo.getNombre());
            estiloEx.setDescripcion(estilo.getDescripcion());
            estiloEx.setInfluencers(estilo.getInfluencers());
            estiloEx.setColores(estilo.getColores());
            return estiloRepository.save(estiloEx);
        }else{return null;}
    }

    public Estilo actualizarEstiloParcial(Long id, Estilo estilo){
        Estilo estiloEx = estiloRepository.findById(id).orElse(null);
        if(estiloEx != null){
            if(estilo.getNombre() != null){
                estiloEx.setNombre(estilo.getNombre());
            }
            if(estilo.getDescripcion() != null){
                estiloEx.setDescripcion(estilo.getDescripcion());
            }
            if(estilo.getInfluencers() != null){
                estiloEx.setInfluencers(estilo.getInfluencers());
            }
            if(estilo.getColores() != null){
                estiloEx.setColores(estilo.getColores());
            }
            return estiloRepository.save(estiloEx);
        }else{return null;}
    }

    public List<Estilo> obtenerEstilosPorInfluencerId(long influencerId){
        return estiloRepository.findByInfluencers_Id(influencerId);

    }

    public List<Estilo> obtenerEstilosPorColorId(long colorId){
        return estiloRepository.findByColores_Id(colorId);
    }

    public List<Estilo> findByInfluencerIdAndColorId(Integer idInfluencer, Integer idColor){
        return estiloRepository.findByInfluencersIdAndColoresId(idInfluencer, idInfluencer);
    }
}
