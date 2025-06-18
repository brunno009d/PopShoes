package com.ribda_PopShoes.cl.popShoes.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.ribda_PopShoes.cl.popShoes.controller.EstiloControllerV2;
import com.ribda_PopShoes.cl.popShoes.model.Estilo;

@Component
public class EstiloModelAssembler implements RepresentationModelAssembler<Estilo, EntityModel<Estilo>>{

    @SuppressWarnings("null")
    @Override
    public EntityModel<Estilo> toModel(Estilo estilo){
        return EntityModel.of(estilo,
            linkTo(methodOn(EstiloControllerV2.class).listar()).withRel("estilos"),
            linkTo(methodOn(EstiloControllerV2.class).buscarEstiloPorId(Long.valueOf(estilo.getId()))).withSelfRel(),
            linkTo(methodOn(EstiloControllerV2.class).actualizar(Long.valueOf(estilo.getId()), estilo)).withRel("actualizar"),
            linkTo(methodOn(EstiloControllerV2.class).editar(Long.valueOf(estilo.getId()), estilo)).withRel("actualizar-parcial"),
            linkTo(methodOn(EstiloControllerV2.class).eliminar(Long.valueOf(estilo.getId()))).withRel("eliminar")
        );
    }

}
