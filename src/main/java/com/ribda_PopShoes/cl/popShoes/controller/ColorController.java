package com.ribda_PopShoes.cl.popShoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ribda_PopShoes.cl.popShoes.model.Color;
import com.ribda_PopShoes.cl.popShoes.service.ColorService;

@RestController
@RequestMapping("/api/v1/colores")
public class ColorController {
    @Autowired
    private ColorService colorService;

    @GetMapping
    public ResponseEntity<List<Color>> listar(){
    List<Color> colores = colorService.obtenerColores();
        if (colores.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(colores);
    }

    @GetMapping("/id")
    public ResponseEntity<Color> buscarColorPorId(Long id){
        Color color = colorService.obtenerColorPorId(id);
        if (color == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(color);
    }

    @PostMapping
    public ResponseEntity<Color> guardar(@RequestBody Color color){
        Color colorNuevo = colorService.guardarColor(color);
        if (colorNuevo == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(colorNuevo);
    }   
    
    @PutMapping("/id")
    public ResponseEntity<Color> actualizar(Long id, @RequestBody Color color){
        Color actColor = colorService.actualizarColor(id, color);
        if(actColor == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actColor);
    }

    @PatchMapping("/id")
    public ResponseEntity<Color> editar(Long id, @RequestBody Color color){
        Color actColor = colorService.actualizarColor(id, color);
        if(actColor == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actColor);
    }    

    @DeleteMapping("/id")
    public ResponseEntity<Void> elminar(Long id){
        colorService.eliminarColor(id);
        return ResponseEntity.noContent().build();
    }
}
