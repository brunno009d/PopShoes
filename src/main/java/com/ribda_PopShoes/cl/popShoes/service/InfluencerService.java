package com.ribda_PopShoes.cl.popShoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ribda_PopShoes.cl.popShoes.model.Influencer;
import com.ribda_PopShoes.cl.popShoes.repository.InfluencerRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class InfluencerService {
    @Autowired
    private InfluencerRepository influencerRepository;

    public List<Influencer> obtenerInfluencers(){
        return influencerRepository.findAll();
    }

    public Influencer obtenerInfluencerPorId(Long id){
        return influencerRepository.findById(id).orElse(null);
    }

    public Influencer guardarInfluencer(Influencer influencer){
        return influencerRepository.save(influencer);
    }

    public void eliminarInfluencer(Long id){
        influencerRepository.deleteById(id);
    }

    public Influencer actualizarInfluencer(Long id, Influencer influencer){
        Influencer influencerEx = influencerRepository.findById(id).orElse(null);
        if(influencerEx != null){
            influencerEx.setNombre(influencer.getNombre());
            influencerEx.setDescripcion(influencer.getDescripcion());
            influencerEx.setEstilos(influencer.getEstilos());
            return influencerRepository.save(influencerEx);
        }else{return null;}
    }

    public Influencer actualizarInfluencerParcial(Long id, Influencer influencer){
        Influencer influencerEx = influencerRepository.findById(id).orElse(null);
        if(influencerEx != null){
            if(influencer.getNombre() != null){
                influencerEx.setNombre(influencer.getNombre());
            }
            if(influencer.getDescripcion() != null){
                influencerEx.setDescripcion(influencer.getDescripcion());
            }
            if(influencer.getEstilos() != null){
                influencerEx.setEstilos(influencer.getEstilos());
            }
            return influencerRepository.save(influencerEx);
        }else{return null;}
    }

}
