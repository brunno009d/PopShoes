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

import com.ribda_PopShoes.cl.popShoes.assemblers.EstiloModelAssembler;
import com.ribda_PopShoes.cl.popShoes.model.Estilo;
import com.ribda_PopShoes.cl.popShoes.service.EstiloService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/v2/estilos")
public class EstiloControllerV2 {
    @Autowired
    private EstiloService estiloService;

    @Autowired
    private EstiloModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Estilo>>> listar(){
        List<EntityModel<Estilo>> estilos = estiloService.obtenerEstilos().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        if (estilos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CollectionModel.of(
            estilos,
            linkTo(methodOn(EstiloControllerV2.class).listar()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Estilo>> buscarEstiloPorId(@PathVariable Long id){
        Estilo estilo = estiloService.obtenerEstiloPorId(id);
        if(estilo == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(estilo));
    }

    @GetMapping(value = "/influencer/{influencerId}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Estilo>> buscarEstiloPorInfluencer(@PathVariable Long influencerId){
        List<EntityModel<Estilo>> estilos = estiloService.obtenerEstilosPorInfluencerId(influencerId)
            .stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(
            estilos,
            linkTo(methodOn(EstiloControllerV2.class).buscarEstiloPorInfluencer(influencerId)).withSelfRel()
        );
    }

    @GetMapping(value = "/color/{colorId}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Estilo>> buscarEstiloPorColor(@PathVariable Long colorId){
        List<EntityModel<Estilo>> estilos = estiloService.obtenerEstilosPorColorId(colorId)
            .stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(
            estilos,
            linkTo(methodOn(EstiloControllerV2.class).buscarEstiloPorColor(colorId)).withSelfRel()
        );
    }

    @GetMapping(value = "/influencer/{i_id}/color/{c_id}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Estilo>> buscarEstiloPorInfluencerYColor(@PathVariable Integer i_id, @PathVariable Integer c_id){
        List<EntityModel<Estilo>> estilos = estiloService.findByInfluencerIdAndColorId(i_id, c_id)
        .stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());

        return CollectionModel.of(
            estilos,
            linkTo(methodOn(EstiloControllerV2.class).buscarEstiloPorInfluencerYColor(i_id, c_id)).withSelfRel()
        );
    }

    @PostMapping(produces =  MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Estilo>> guardar(@RequestBody Estilo estilo){
        Estilo nuevoEstilo = estiloService.guardarEstilo(estilo);

        return ResponseEntity
            .created(linkTo(methodOn(EstiloControllerV2.class).buscarEstiloPorId(Long.valueOf(nuevoEstilo.getId()))).toUri())
            .body(assembler.toModel(nuevoEstilo));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Estilo>> actualizar(@PathVariable Long id, @RequestBody Estilo estilo){
        estilo.setId((id.intValue()));
        Estilo actEstilo = estiloService.actualizarEstilo(id, estilo);
        if (actEstilo == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(actEstilo));
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Estilo>> editar(@PathVariable Long id, @RequestBody Estilo estilo){
        Estilo actEstilo = estiloService.actualizarEstiloParcial(id, estilo);
        if (actEstilo == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(actEstilo));
    }
    
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        Estilo eEstilo = estiloService.obtenerEstiloPorId(id);
        if (eEstilo == null){
            return ResponseEntity.notFound().build();
        }
        estiloService.ElminarEstilo(id);
        return ResponseEntity.noContent().build();
    }



}
