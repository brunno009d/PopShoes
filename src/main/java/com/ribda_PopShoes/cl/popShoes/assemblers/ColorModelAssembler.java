package com.ribda_PopShoes.cl.popShoes.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.ribda_PopShoes.cl.popShoes.controller.CalzadoControllerV2;
import com.ribda_PopShoes.cl.popShoes.controller.ColorControllerV2;
import com.ribda_PopShoes.cl.popShoes.model.Color;

@Component
public class ColorModelAssembler implements RepresentationModelAssembler<Color, EntityModel<Color>>{

    @SuppressWarnings("null")
    @Override
    public EntityModel<Color> toModel(Color color){
        return EntityModel.of(color,
            linkTo(methodOn(ColorControllerV2.class).listar()).withRel("colores"),
            linkTo(methodOn(ColorControllerV2.class).buscarColorPorId(Long.valueOf(color.getId()))).withSelfRel(),
            linkTo(methodOn(ColorControllerV2.class).actualizar(Long.valueOf(color.getId()), color)).withRel("actualizar"),
            linkTo(methodOn(ColorControllerV2.class).elminar(Long.valueOf(color.getId()))).withRel("eliminar")
        );
    }
}
