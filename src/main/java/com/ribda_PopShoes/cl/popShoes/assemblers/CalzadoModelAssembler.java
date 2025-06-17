package com.ribda_PopShoes.cl.popShoes.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.ribda_PopShoes.cl.popShoes.controller.CalzadoControllerV2;
import com.ribda_PopShoes.cl.popShoes.model.Calzado;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Component
public class CalzadoModelAssembler implements RepresentationModelAssembler<Calzado, EntityModel<Calzado>>{

    @SuppressWarnings("null")
    @Override
    public EntityModel<Calzado> toModel(Calzado calzado){
        return EntityModel.of(calzado,
                linkTo(methodOn(CalzadoControllerV2.class).listar()).withRel("calzados"),
                linkTo(methodOn(CalzadoControllerV2.class).buscarCalzadoPorId(Long.valueOf(calzado.getId()))).withSelfRel(),
                linkTo(methodOn(CalzadoControllerV2.class).actualizar(Long.valueOf(calzado.getId()), calzado)).withRel("actualizar"),
                linkTo(methodOn(CalzadoControllerV2.class).editar(Long.valueOf(calzado.getId()), calzado)).withRel("actualizar-parcial"),
                linkTo(methodOn(CalzadoControllerV2.class).eliminar(Long.valueOf(calzado.getId()))).withRel("eliminar")

        );
    }

}
