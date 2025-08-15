package com.backend.SisLogisticaApplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendamento")
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faixa_horaria_id", nullable = false)
    @NotNull
    private FaixaHoraria faixaHoraria;

    @NotNull
    @Column(name = "id_pedido", nullable = false)
    private Long idPedido;

    @NotBlank
    @Size(max = 80)
    @Column(nullable = false)
    private String fornecedor;

    @NotBlank
    @Email
    @Size(max = 80)
    @Column(name = "email_fornecedor", nullable = false)
    private String emailFornecedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_caminhao_id", nullable = false)
    @NotNull
    private TipoCaminhao tipoCaminhao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_paletizacao_id", nullable = false)
    @NotNull
    private TipoPaletizacao tipoPaletizacao;

    @Min(1)
    @Column(name = "quantidade_pallets", nullable = false)
    private Integer quantidadePallets;

    @Size(max = 500)
    private String observacao;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "email_enviado")
    private Boolean emailEnviado = false;

    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
    }


    public Agendamento() {}


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public FaixaHoraria getFaixaHoraria() { return faixaHoraria; }
    public void setFaixaHoraria(FaixaHoraria faixaHoraria) { this.faixaHoraria = faixaHoraria; }

    public Long getIdPedido() { return idPedido; }
    public void setIdPedido(Long idPedido) { this.idPedido = idPedido; }

    public String getFornecedor() { return fornecedor; }
    public void setFornecedor(String fornecedor) { this.fornecedor = fornecedor; }

    public String getEmailFornecedor() { return emailFornecedor; }
    public void setEmailFornecedor(String emailFornecedor) { this.emailFornecedor = emailFornecedor; }

    public TipoCaminhao getTipoCaminhao() { return tipoCaminhao; }
    public void setTipoCaminhao(TipoCaminhao tipoCaminhao) { this.tipoCaminhao = tipoCaminhao; }

    public TipoPaletizacao getTipoPaletizacao() { return tipoPaletizacao; }
    public void setTipoPaletizacao(TipoPaletizacao tipoPaletizacao) { this.tipoPaletizacao = tipoPaletizacao; }

    public Integer getQuantidadePallets() { return quantidadePallets; }
    public void setQuantidadePallets(Integer quantidadePallets) { this.quantidadePallets = quantidadePallets; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public Boolean getEmailEnviado() { return emailEnviado; }
    public void setEmailEnviado(Boolean emailEnviado) { this.emailEnviado = emailEnviado; }
}

