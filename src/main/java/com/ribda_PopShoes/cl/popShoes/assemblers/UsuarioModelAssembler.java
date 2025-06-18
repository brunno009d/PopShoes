package com.ribda_PopShoes.cl.popShoes.assemblers;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.hibernate.type.descriptor.jdbc.LongNVarcharJdbcType;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.ribda_PopShoes.cl.popShoes.controller.UsuarioControllerV2;
import com.ribda_PopShoes.cl.popShoes.model.Usuario;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>>{

    @SuppressWarnings("null")
    @Override
    public EntityModel<Usuario> toModel(Usuario usuario){
        return EntityModel.of(usuario,
            linkTo(methodOn(UsuarioControllerV2.class).listar()).withRel("usuarios"),
            linkTo(methodOn(UsuarioControllerV2.class).buscarUsuarioPorId(Long.valueOf(usuario.getId()))).withSelfRel(),
            linkTo(methodOn(UsuarioControllerV2.class).actualizar(Long.valueOf(usuario.getId()), usuario)).withRel("actualizar"),
            linkTo(methodOn(UsuarioControllerV2.class).editar(Long.valueOf(usuario.getId()), usuario)).withRel("actualizar-parcial"),
            linkTo(methodOn(UsuarioControllerV2.class).eliminar(Long.valueOf(usuario.getId()))).withRel("eliminar")
        );
    }
}
