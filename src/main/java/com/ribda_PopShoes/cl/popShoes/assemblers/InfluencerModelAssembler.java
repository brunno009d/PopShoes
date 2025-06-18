package com.ribda_PopShoes.cl.popShoes.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.ribda_PopShoes.cl.popShoes.controller.InfluencerControllerV2;
import com.ribda_PopShoes.cl.popShoes.model.Influencer;

@Component
public class InfluencerModelAssembler implements RepresentationModelAssembler<Influencer, EntityModel<Influencer>>{

    @SuppressWarnings("null")
    @Override
    public EntityModel<Influencer> toModel(Influencer influencer){
        return EntityModel.of(influencer,
            linkTo(methodOn(InfluencerControllerV2.class).listar()).withRel("influencers"),
            linkTo(methodOn(InfluencerControllerV2.class).buscarInfluencerPorId(Long.valueOf(influencer.getId()))).withSelfRel(),
            linkTo(methodOn(InfluencerControllerV2.class).actualizar(Long.valueOf(influencer.getId()), influencer)).withRel("actualizar"),
            linkTo(methodOn(InfluencerControllerV2.class).eliminar(Long.valueOf(influencer.getId()))).withRel("eliminar")
        );
    }
}
