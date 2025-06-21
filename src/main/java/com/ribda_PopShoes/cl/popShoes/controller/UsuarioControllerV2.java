package com.ribda_PopShoes.cl.popShoes.controller;

import java.util.List;
import java.util.Map;
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

import com.ribda_PopShoes.cl.popShoes.assemblers.UsuarioModelAssembler;
import com.ribda_PopShoes.cl.popShoes.model.Usuario;
import com.ribda_PopShoes.cl.popShoes.service.UsuarioService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/v2/usuarios")
public class UsuarioControllerV2 {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Usuario>>> listar(){
        List<EntityModel<Usuario>> usuarios = usuarioService.obtenerUsuarios().stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CollectionModel.of(
            usuarios,
            linkTo(methodOn(UsuarioControllerV2.class).listar()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Usuario>> buscarUsuarioPorId(@PathVariable Long id){
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        if (usuario == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(usuario));
    }

    @GetMapping("/resumen-usuario")
    public ResponseEntity<List<Map<String, Object>>> resumen(){
        List<Map<String, Object>> resumen = usuarioService.obtenerUsuarioConNombres();
        if (resumen.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resumen);
    }

    @GetMapping("/resumen-usuario-influencer")
    public ResponseEntity<List<Map<String, Object>>> resumenUsuarioInfluencer(){
        List<Map<String, Object>> resumen = usuarioService.obtenerUsuarioConInfluencers();
        if (resumen.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resumen);
    }

    @GetMapping("/resumen-usuario-color")
    public ResponseEntity<List<Map<String, Object>>> resumenUsuarioColor(){
        List<Map<String, Object>> resumen = usuarioService.obtenerUsuarioConColores();
        if (resumen.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resumen);
    }


    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Usuario>> guardar(@RequestBody Usuario usuario){
        Usuario nuevoUsuario = usuarioService.guardarUsuario(usuario);
        
        return ResponseEntity
        .created(linkTo(methodOn(UsuarioControllerV2.class).buscarUsuarioPorId(Long.valueOf(nuevoUsuario.getId()))).toUri())
        .body(assembler.toModel(nuevoUsuario));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Usuario>> actualizar(@PathVariable Long id, @RequestBody Usuario usuario){
        usuario.setId(id.intValue());
        Usuario actulizarUsuario = usuarioService.actualizUsuario(id, usuario);
        if (actulizarUsuario == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(actulizarUsuario));
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Usuario>> editar(@PathVariable Long id, @RequestBody Usuario usuario){
        Usuario actualizarUsuario = usuarioService.actualizarUsuarioParcial(id, usuario);
        if(actualizarUsuario == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(actualizarUsuario));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        Usuario eUsuario = usuarioService.obtenerUsuarioPorId(id);
        if(eUsuario == null){
            return ResponseEntity.notFound().build();
        }
        
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
