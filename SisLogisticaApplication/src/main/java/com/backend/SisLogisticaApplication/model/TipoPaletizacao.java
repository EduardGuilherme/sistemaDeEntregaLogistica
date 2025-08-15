package com.backend.SisLogisticaApplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tipo_paletizacao")
public class TipoPaletizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, unique = true)
    private String nome;

    @Column(name = "dobra_quantidade")
    private Boolean dobraQuantidade = false;

    // Construtores
    public TipoPaletizacao() {}

    public TipoPaletizacao(String nome, Boolean dobraQuantidade) {
        this.nome = nome;
        this.dobraQuantidade = dobraQuantidade;
    }


    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Boolean getDobraQuantidade() { return dobraQuantidade; }
    public void setDobraQuantidade(Boolean dobraQuantidade) { this.dobraQuantidade = dobraQuantidade; }
}

