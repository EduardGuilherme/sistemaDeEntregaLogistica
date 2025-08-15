package com.backend.SisLogisticaApplication.dtos;

import java.time.LocalDate;

public class OcupacaoDTO {
    private LocalDate data;
    private Long faixaHorariaId;
    private String faixaHorariaDescricao;
    private Integer totalPallets;
    private Double porcentagemOcupacao;
    private Boolean excedeLimite;

    public OcupacaoDTO(LocalDate data, Long faixaHorariaId, String faixaHorariaDescricao,
                       Integer totalPallets, Double porcentagemOcupacao, Boolean excedeLimite) {
        this.data = data;
        this.faixaHorariaId = faixaHorariaId;
        this.faixaHorariaDescricao = faixaHorariaDescricao;
        this.totalPallets = totalPallets;
        this.porcentagemOcupacao = porcentagemOcupacao;
        this.excedeLimite = excedeLimite;
    }


    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public Long getFaixaHorariaId() { return faixaHorariaId; }
    public void setFaixaHorariaId(Long faixaHorariaId) { this.faixaHorariaId = faixaHorariaId; }

    public String getFaixaHorariaDescricao() { return faixaHorariaDescricao; }
    public void setFaixaHorariaDescricao(String faixaHorariaDescricao) { this.faixaHorariaDescricao = faixaHorariaDescricao; }

    public Integer getTotalPallets() { return totalPallets; }
    public void setTotalPallets(Integer totalPallets) { this.totalPallets = totalPallets; }

    public Double getPorcentagemOcupacao() { return porcentagemOcupacao; }
    public void setPorcentagemOcupacao(Double porcentagemOcupacao) { this.porcentagemOcupacao = porcentagemOcupacao; }

    public Boolean getExcedeLimite() { return excedeLimite; }
    public void setExcedeLimite(Boolean excedeLimite) { this.excedeLimite = excedeLimite; }
}