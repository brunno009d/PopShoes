package com.ribda_PopShoes.cl.popShoes.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estilo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estilo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String descripcion;

    @ManyToMany
    @JoinTable(
        name = "estilo_influencers",
        joinColumns = @JoinColumn(name = "FK_ESTILO", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "FK_INFLUENCERS", nullable = false)
    )
    private List<Influencer> influencers;

    @ManyToMany
    @JoinTable(
        name = "estilo_color",
        joinColumns = @JoinColumn(name = "FK_ESTILO", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "FK_COLOR", nullable = false)
    )
    private List<Color> colores;

}
