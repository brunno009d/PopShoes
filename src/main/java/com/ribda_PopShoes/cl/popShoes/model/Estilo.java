package com.ribda_PopShoes.cl.popShoes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @ManyToMany
    @JoinColumn(name= "id_influencer", nullable = false)
    private Influencer influencer;

    @ManyToMany
    @JoinColumn(name= "id_color", nullable = false)
    private Color color;
}
