package com.ribda_PopShoes.cl.popShoes.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import javax.print.attribute.standard.Media;

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

import com.ribda_PopShoes.cl.popShoes.assemblers.ColorModelAssembler;
import com.ribda_PopShoes.cl.popShoes.model.Color;
import com.ribda_PopShoes.cl.popShoes.service.ColorService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/v2/colores")
public class ColorControllerV2 {
    @Autowired
    private ColorService colorService;

    @Autowired
    private ColorModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Color>>> listar(){
    List<EntityModel<Color>> colores = colorService.obtenerColores().stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());

        if (colores.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CollectionModel.of(
            colores,
            linkTo(methodOn(ColorControllerV2.class).listar()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Color>> buscarColorPorId(@PathVariable Long id){
        Color color = colorService.obtenerColorPorId(id);
        if (color == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(color));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Color>> guardar(@RequestBody Color color){
        Color colorNuevo = colorService.guardarColor(color);
        
        return ResponseEntity
        .created(linkTo(methodOn(ColorControllerV2.class).buscarColorPorId(Long.valueOf(colorNuevo.getId()))).toUri())
        .body(assembler.toModel(colorNuevo));
    }   
    
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Color>> actualizar(@PathVariable Long id, @RequestBody Color color){
        color.setId(id.intValue());
        Color actColor = colorService.actualizarColor(id, color);
        if(actColor == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(actColor));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> elminar(@PathVariable Long id){
        Color eColor = colorService.obtenerColorPorId(id);
        if (eColor == null){
            return ResponseEntity.notFound().build();
        }

        colorService.eliminarColor(id);
        return ResponseEntity.noContent().build();
    }
}
