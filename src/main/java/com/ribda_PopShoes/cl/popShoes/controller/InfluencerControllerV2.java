package com.ribda_PopShoes.cl.popShoes.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
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

import com.ribda_PopShoes.cl.popShoes.assemblers.InfluencerModelAssembler;
import com.ribda_PopShoes.cl.popShoes.model.Influencer;
import com.ribda_PopShoes.cl.popShoes.service.InfluencerService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
@RequestMapping("/api/v2/influencers")
public class InfluencerControllerV2 {
    @Autowired
    private InfluencerService influencerService;

    @Autowired
    private InfluencerModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Influencer>>> listar() {
        List<EntityModel<Influencer>> influencers = influencerService.obtenerInfluencers().stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());

        if(influencers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CollectionModel.of(
            influencers,
            linkTo(methodOn(InfluencerControllerV2.class).listar()).withSelfRel()
            ));
    }

    @GetMapping(value = "/id", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Influencer>> buscarInfluencerPorId(@PathVariable Long id){
        Influencer influencer = influencerService.obtenerInfluencerPorId(id);
        if (influencer == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(assembler.toModel(influencer));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Influencer>> guardar(@RequestBody Influencer influencer){
        Influencer nuevoInfluencer = influencerService.guardarInfluencer(influencer);
        
        return ResponseEntity
        .created(linkTo(methodOn(InfluencerControllerV2.class).buscarInfluencerPorId(Long.valueOf(nuevoInfluencer.getId()))).toUri())
        .body(assembler.toModel(nuevoInfluencer));
    }

    @PutMapping(value = "/{id}", produces =  MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Influencer>> actualizar(@PathVariable Long id, @RequestBody Influencer influencer){
        influencer.setId(id.intValue());
        Influencer actInfluencer = influencerService.actualizarInfluencer(id, influencer);
        if (actInfluencer == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(actInfluencer));
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Influencer>> editar(@PathVariable Long id, @RequestBody Influencer influencer){
        Influencer actInfluencer = influencerService.actualizarInfluencerParcial(id, influencer);
        if (actInfluencer == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(actInfluencer));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        Influencer influencer = influencerService.obtenerInfluencerPorId(id);
        if(influencer == null){
            return ResponseEntity.notFound().build();
        }

        influencerService.eliminarInfluencer(id);
        return ResponseEntity.noContent().build();
    }
}
