package com.ribda_PopShoes.cl.popShoes.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
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

import com.ribda_PopShoes.cl.popShoes.assemblers.CalzadoModelAssembler;
import com.ribda_PopShoes.cl.popShoes.model.Calzado;
import com.ribda_PopShoes.cl.popShoes.service.CalzadoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("api/v2/calzados")
@Tag(name = "Api que administra los calzados")
public class CalzadoControllerV2 {
    @Autowired
    private CalzadoService calzadoService;

    @Autowired
    private CalzadoModelAssembler assembler;

    @GetMapping(produces =  MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Esta api llama a todos los calzados", description = "Esta api se encarga de obtener todos los calzados que hay")
    public ResponseEntity<CollectionModel<EntityModel<Calzado>>> listar(){
        List<EntityModel<Calzado>> calzados = calzadoService.obtenerCalzados().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        if (calzados.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CollectionModel.of(
            calzados,
            linkTo(methodOn(CalzadoControllerV2.class).listar()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Esta api llama a un calzado por su id", description = "Esta api se encarga de obtener un calzado por id")
    public ResponseEntity<EntityModel<Calzado>> buscarCalzadoPorId(@PathVariable Long id){
        Calzado calzado = calzadoService.obtenerCalzadoPorId(id);
        if(calzado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(calzado));
    }

    @GetMapping(value = "/{nombre}/talla/{numero}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Esta api llama a un calzado por su nombre y su talla", description = "Esta api se encarga de obtener un calzado por su nombre y su talla")
    public CollectionModel<EntityModel<Calzado>> buscarCalzadoPorNombreYTalla(@PathVariable String nombre, @PathVariable Integer numero){
        List<EntityModel<Calzado>> calzados = calzadoService.findByCalzadoNombreAndTalla(nombre, numero)
            .stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(
            calzados,
            linkTo(methodOn(CalzadoControllerV2.class).buscarCalzadoPorNombreYTalla(nombre, numero)).withSelfRel()
        );
    }

    @GetMapping(value = "/categoria/{c_id}/marca/{m_id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Esta api llama a un calzado por la id de una categoria y una marca", description = "Esta api se encarga de obtener un calzado por la id de una categoria y una marca")
    public CollectionModel<EntityModel<Calzado>> buscarCalzadoPorCategoriaYMarca(@PathVariable Integer c_id, @PathVariable Integer m_id){
            List<EntityModel<Calzado>> calzados = calzadoService.findByCaregoriaIdAndMarcaId(c_id, m_id)
            .stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(
            calzados,
            linkTo(methodOn(CalzadoControllerV2.class).buscarCalzadoPorCategoriaYMarca(c_id, m_id)).withSelfRel()
        );
    }

    @GetMapping(value = "/estilo/{e_id}/categoria/{c_id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Esta api llama a un calzado por la id de un estilo y una categoria", description = "Esta api se encarga de obtener un calzado por la id de un estilo y el id de una categoria")
    public CollectionModel<EntityModel<Calzado>> buscarCalzadoPorEstiloYCategoria(@PathVariable Integer e_id,@PathVariable Integer c_id){
            List<EntityModel<Calzado>> calzados = calzadoService.findByEstiloIdAndCategoriaId(e_id, c_id)
            .stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(
            calzados,
            linkTo(methodOn(CalzadoControllerV2.class).buscarCalzadoPorEstiloYCategoria(e_id, c_id)).withSelfRel()
        );
    }

    @GetMapping("/resumen-calzado")
    @Operation(summary = "Esta api muestra calzados con la cantidad de usuarios", description = "Esta api se encarga de mostrar calzados con la cantidad de usuarios que tienen ese calzado")
    public ResponseEntity<List<Map<String, Object>>> resumenCalzado(){
        List<Map<String, Object>> resumen = calzadoService.obtenerCalzadosConNombres();
        if (resumen.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resumen);
    }

    @GetMapping("/resumen-calzado-color")
    @Operation(summary = "Esta api muestra calzados con sus colores", description = "Esta api se encarga de mostrar calzados con sus colores")
    public ResponseEntity<List<Map<String, Object>>> resumenCalzadoColor(){
        List<Map<String, Object>> resumen = calzadoService.obtenerCalzadosConColores();
        if (resumen.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resumen);
    }

    @GetMapping("/resumen-calzado-influencer")
    @Operation(summary = "Esta api muestra calzados con sus influencers", description = "Esta api se encarga de mostrar calzados con sus influencers")
    public ResponseEntity<List<Map<String, Object>>> resumenCalzadoInfluencer(){
        List<Map<String, Object>> resumen = calzadoService.obtenerCalzadosConInfluencer();
        if (resumen.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resumen);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Esta api crea calzados", description = "Esta api se encarga de crear un nuevo calzado")
    public ResponseEntity<EntityModel<Calzado>> guardar(@RequestBody Calzado calzado){
        Calzado nuevoCalzado = calzadoService.guardarCalzado(calzado);

        return ResponseEntity
            .created(linkTo(methodOn(CalzadoControllerV2.class).buscarCalzadoPorId(Long.valueOf(nuevoCalzado.getId()))).toUri())
            .body(assembler.toModel(nuevoCalzado));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Esta api actualiza un calzado", description = "Esta api se encarga de actualizar un calzado existente")
    public ResponseEntity<EntityModel<Calzado>> actualizar(@PathVariable Long id, @RequestBody Calzado calzado){
        calzado.setId(id.intValue());
        Calzado actCalzado = calzadoService.guardarCalzado(calzado);
        if (actCalzado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(actCalzado));
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Esta api actualiza parcialmente un calzado", description = "esta api se encarga de actualizar parcialmente un calzado existente")
    public ResponseEntity<EntityModel<Calzado>> editar(@PathVariable Long id, @RequestBody Calzado calzado){
        Calzado actCalzado = calzadoService.actualizarCalzadoParcial(id, calzado);
        if (actCalzado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(actCalzado));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Esta api elimina un calzado", description = "esta api se encarga de eliminar un calzado existente")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        Calzado eCalzado = calzadoService.obtenerCalzadoPorId(id);
        if (eCalzado == null){
            return ResponseEntity.notFound().build();
        }
        
        calzadoService.eliminarCalzado(id);
        return ResponseEntity.noContent().build();
    }

    
}
