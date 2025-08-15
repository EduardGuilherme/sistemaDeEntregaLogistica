package com.backend.SisLogisticaApplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tipo_caminhao")
public class TipoCaminhao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, unique = true)
    private String nome;

    @Size(max = 200)
    private String descricao;


    public TipoCaminhao() {}

    public TipoCaminhao(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}


