package com.ribda_PopShoes.cl.popShoes.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apaterno;

    @Column(nullable = false, length = 100)
    private String amaterno;

    @Column(unique = true, length = 100, nullable = false)
    private String usuario;

    @Column(nullable = false, length = 50)
    @JsonIgnore
    private String contraseña;

    @Column(nullable = false, length = 100)
    private String direccion;

    @Column(nullable = false, length = 15)
    private Integer telefono;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    @ManyToMany
    @JoinTable(
        name = "usuario_calzados",
        joinColumns = @JoinColumn(name = "FK_USUARIO", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "FK_CALZADOS", nullable = false)
    )
    private List<Calzado> calzados;

}
