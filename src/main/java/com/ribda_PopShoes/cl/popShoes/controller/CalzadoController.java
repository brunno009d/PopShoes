package com.ribda_PopShoes.cl.popShoes.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.ribda_PopShoes.cl.popShoes.model.Calzado;
import com.ribda_PopShoes.cl.popShoes.service.CalzadoService;

@RestController
@RequestMapping("api/v1/calzados")
public class CalzadoController {
    @Autowired
    private CalzadoService calzadoService;

    @GetMapping
    public ResponseEntity<List<Calzado>> listar(){
        List<Calzado> calzados = calzadoService.obtenerCalzados();
        if (calzados.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(calzados);
    }

    @GetMapping("/{id}")
    public ResponseEntity <Calzado> buscarCalzadoPorId(@PathVariable Long id){
        Calzado calzado = calzadoService.obtenerCalzadoPorId(id);
        if(calzado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(calzado);
    }

    @GetMapping("/resumen")
    public ResponseEntity<List<Map<String, Object>>> resumen(){
        List<Map<String, Object>> resumen = calzadoService.obtenerCalzadosConNombres();
        if (resumen.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resumen);
    }

    @PostMapping
    public ResponseEntity<Calzado> guardar(@RequestBody Calzado calzado){
        Calzado nuevoCalzado = calzadoService.guardarCalzado(calzado);
        if (nuevoCalzado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(nuevoCalzado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Calzado> actualizar(@PathVariable Long id, @RequestBody Calzado calzado){
        Calzado actCalzado = calzadoService.actualizarCalzado(id, calzado);
        if (actCalzado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actCalzado);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Calzado> editar(@PathVariable Long id, @RequestBody Calzado calzado){
        Calzado actCalzado = calzadoService.actualizarCalzadoParcial(id, calzado);
        if (actCalzado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actCalzado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        calzadoService.elminarCalzado(id);
        return ResponseEntity.notFound().build();
    }

    
}
