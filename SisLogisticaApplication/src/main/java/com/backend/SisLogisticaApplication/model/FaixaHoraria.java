package com.backend.SisLogisticaApplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
@Table(name = "faixa_horaria")
public class FaixaHoraria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "horario_inicio", nullable = false)
    private LocalTime horarioInicio;

    @NotNull
    @Column(name = "horario_fim", nullable = false)
    private LocalTime horarioFim;

    private String descricao;


    public FaixaHoraria() {}

    public FaixaHoraria(LocalTime horarioInicio, LocalTime horarioFim, String descricao) {
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.descricao = descricao;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalTime getHorarioInicio() { return horarioInicio; }
    public void setHorarioInicio(LocalTime horarioInicio) { this.horarioInicio = horarioInicio; }

    public LocalTime getHorarioFim() { return horarioFim; }
    public void setHorarioFim(LocalTime horarioFim) { this.horarioFim = horarioFim; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}