package com.backend.SisLogisticaApplication.dtos;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class AgendamentoDTO {
    private Long id;

    @NotNull(message = "Data é obrigatória")
    private LocalDate data;

    @NotNull(message = "Faixa horária é obrigatória")
    private Long faixaHorariaId;

    @NotNull(message = "ID do pedido é obrigatório")
    private Long idPedido;

    @NotBlank(message = "Fornecedor é obrigatório")
    @Size(max = 80, message = "Fornecedor deve ter no máximo 80 caracteres")
    private String fornecedor;

    @NotBlank(message = "Email do fornecedor é obrigatório")
    @Email(message = "Email deve ser válido")
    @Size(max = 80, message = "Email deve ter no máximo 80 caracteres")
    private String emailFornecedor;

    @NotNull(message = "Tipo de caminhão é obrigatório")
    private Long tipoCaminhaoId;

    @NotNull(message = "Tipo de paletização é obrigatório")
    private Long tipoPaletizacaoId;

    @NotNull(message = "Quantidade de pallets é obrigatória")
    @Min(value = 1, message = "Quantidade deve ser maior que zero")
    private Integer quantidadePallets;

    @Size(max = 500, message = "Observação deve ter no máximo 500 caracteres")
    private String observacao;


    private String faixaHorariaDescricao;
    private String tipoCaminhaoNome;
    private String tipoPaletizacaoNome;
    private Double ocupacaoDia;
    private Double ocupacaoFaixa;


    public AgendamentoDTO() {}


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public Long getFaixaHorariaId() { return faixaHorariaId; }
    public void setFaixaHorariaId(Long faixaHorariaId) { this.faixaHorariaId = faixaHorariaId; }

    public Long getIdPedido() { return idPedido; }
    public void setIdPedido(Long idPedido) { this.idPedido = idPedido; }

    public String getFornecedor() { return fornecedor; }
    public void setFornecedor(String fornecedor) { this.fornecedor = fornecedor; }

    public String getEmailFornecedor() { return emailFornecedor; }
    public void setEmailFornecedor(String emailFornecedor) { this.emailFornecedor = emailFornecedor; }

    public Long getTipoCaminhaoId() { return tipoCaminhaoId; }
    public void setTipoCaminhaoId(Long tipoCaminhaoId) { this.tipoCaminhaoId = tipoCaminhaoId; }

    public Long getTipoPaletizacaoId() { return tipoPaletizacaoId; }
    public void setTipoPaletizacaoId(Long tipoPaletizacaoId) { this.tipoPaletizacaoId = tipoPaletizacaoId; }

    public Integer getQuantidadePallets() { return quantidadePallets; }
    public void setQuantidadePallets(Integer quantidadePallets) { this.quantidadePallets = quantidadePallets; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    public String getFaixaHorariaDescricao() { return faixaHorariaDescricao; }
    public void setFaixaHorariaDescricao(String faixaHorariaDescricao) { this.faixaHorariaDescricao = faixaHorariaDescricao; }

    public String getTipoCaminhaoNome() { return tipoCaminhaoNome; }
    public void setTipoCaminhaoNome(String tipoCaminhaoNome) { this.tipoCaminhaoNome = tipoCaminhaoNome; }

    public String getTipoPaletizacaoNome() { return tipoPaletizacaoNome; }
    public void setTipoPaletizacaoNome(String tipoPaletizacaoNome) { this.tipoPaletizacaoNome = tipoPaletizacaoNome; }

    public Double getOcupacaoDia() { return ocupacaoDia; }
    public void setOcupacaoDia(Double ocupacaoDia) { this.ocupacaoDia = ocupacaoDia; }

    public Double getOcupacaoFaixa() { return ocupacaoFaixa; }
    public void setOcupacaoFaixa(Double ocupacaoFaixa) { this.ocupacaoFaixa = ocupacaoFaixa; }
}