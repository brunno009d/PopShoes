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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("api/v2/calzados")
public class CalzadoControllerV2 {
    @Autowired
    private CalzadoService calzadoService;

    @Autowired
    private CalzadoModelAssembler assembler;

    @GetMapping(produces =  MediaTypes.HAL_JSON_VALUE)
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
    public ResponseEntity<EntityModel<Calzado>> buscarCalzadoPorId(@PathVariable Long id){
        Calzado calzado = calzadoService.obtenerCalzadoPorId(id);
        if(calzado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(calzado));
    }

    @GetMapping(value = "/{nombre}/talla/{numero}", produces = MediaTypes.HAL_JSON_VALUE)
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
    public ResponseEntity<List<Map<String, Object>>> resumenCalzado(){
        List<Map<String, Object>> resumen = calzadoService.obtenerCalzadosConNombres();
        if (resumen.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resumen);
    }

    @GetMapping("/resumen-calzado-color")
    public ResponseEntity<List<Map<String, Object>>> resumenCalzadoColor(){
        List<Map<String, Object>> resumen = calzadoService.obtenerCalzadosConColores();
        if (resumen.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resumen);
    }

    @GetMapping("/resumen-calzado-influencer")
    public ResponseEntity<List<Map<String, Object>>> resumenCalzadoInfluencer(){
        List<Map<String, Object>> resumen = calzadoService.obtenerCalzadosConInfluencer();
        if (resumen.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resumen);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Calzado>> guardar(@RequestBody Calzado calzado){
        Calzado nuevoCalzado = calzadoService.guardarCalzado(calzado);

        return ResponseEntity
            .created(linkTo(methodOn(CalzadoControllerV2.class).buscarCalzadoPorId(Long.valueOf(nuevoCalzado.getId()))).toUri())
            .body(assembler.toModel(nuevoCalzado));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Calzado>> actualizar(@PathVariable Long id, @RequestBody Calzado calzado){
        calzado.setId(id.intValue());
        Calzado actCalzado = calzadoService.guardarCalzado(calzado);
        if (actCalzado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(actCalzado));
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Calzado>> editar(@PathVariable Long id, @RequestBody Calzado calzado){
        Calzado actCalzado = calzadoService.actualizarCalzadoParcial(id, calzado);
        if (actCalzado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(actCalzado));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        Calzado eCalzado = calzadoService.obtenerCalzadoPorId(id);
        if (eCalzado == null){
            return ResponseEntity.notFound().build();
        }
        
        calzadoService.elminarCalzado(id);
        return ResponseEntity.noContent().build();
    }

    
}
