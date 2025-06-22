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

import com.ribda_PopShoes.cl.popShoes.model.Estilo;
import com.ribda_PopShoes.cl.popShoes.service.EstiloService;

@RestController
@RequestMapping("/api/v1/estilos")
public class EstiloController {
    @Autowired
    private EstiloService estiloService;

    @GetMapping
    public ResponseEntity<List<Estilo>> listar(){
        List<Estilo> estilos = estiloService.obtenerEstilos();
        if (estilos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estilos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estilo> buscarEstiloPorId(@PathVariable Long id){
        Estilo estilo = estiloService.obtenerEstiloPorId(id);
        if(estilo == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estilo);
    }

    @GetMapping("/influencer/{influencerId}")
    public ResponseEntity<List<Estilo>> buscarEstiloPorInfluencer(@PathVariable Long influencerId){
        List<Estilo> estilos = estiloService.obtenerEstilosPorInfluencerId(influencerId);
        if (estilos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estilos);
    }

    @GetMapping("/color/{colorId}")
    public ResponseEntity<List<Estilo>> buscarEstiloPorColor(@PathVariable Long colorId){
        List<Estilo> estilos = estiloService.obtenerEstilosPorColorId(colorId);
        if (estilos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estilos);
    }

    @PostMapping
    public ResponseEntity<Estilo> guardar(@RequestBody Estilo estilo){
        Estilo nuevoEstilo = estiloService.guardarEstilo(estilo);
        return ResponseEntity.status(201).body(nuevoEstilo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estilo> actualizar(@PathVariable Long id, @RequestBody Estilo estilo){
        Estilo actEstilo = estiloService.actualizarEstilo(id, estilo);
        if (actEstilo == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actEstilo);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Estilo> editar(@PathVariable Long id, @RequestBody Estilo estilo){
        Estilo actEstilo = estiloService.actualizarEstiloParcial(id, estilo);
        if (actEstilo == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actEstilo);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        estiloService.eliminarEstilo(id);
        return ResponseEntity.noContent().build();
    }



}
