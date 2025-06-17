package com.ribda_PopShoes.cl.popShoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ribda_PopShoes.cl.popShoes.model.Influencer;
import com.ribda_PopShoes.cl.popShoes.service.InfluencerService;

@RestController
@RequestMapping("/api/v2/influencers")
public class InfluencerControllerV2 {
    @Autowired
    private InfluencerService influencerService;

    @GetMapping
    public ResponseEntity<List<Influencer>> listar() {
        List<Influencer> influencers = influencerService.obtenerInfluencers();
        if(influencers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(influencers);
    }

    @GetMapping("/id")
    public ResponseEntity<Influencer> buscarInfluencerPorId(@PathVariable Long id){
        Influencer influencer = influencerService.obtenerInfluencerPorId(id);
        if (influencer == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(influencer);
    }

    @PostMapping
    public ResponseEntity<Influencer> guardar(@RequestBody Influencer influencer){
        Influencer nuevoInfluencer = influencerService.guardarInfluencer(influencer);
        return ResponseEntity.status(201).body(nuevoInfluencer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Influencer> actualizar(@PathVariable Long id, @RequestBody Influencer influencer){
        Influencer actInfluencer = influencerService.actualizarInfluencer(id, influencer);
        if (actInfluencer == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actInfluencer);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Influencer> editar(@PathVariable Long id, @RequestBody Influencer influencer){
        Influencer actInfluencer = influencerService.actualizarInfluencerParcial(id, influencer);
        if (actInfluencer == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actInfluencer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        influencerService.eliminarInfluencer(id);
        return ResponseEntity.noContent().build();
    }
}
