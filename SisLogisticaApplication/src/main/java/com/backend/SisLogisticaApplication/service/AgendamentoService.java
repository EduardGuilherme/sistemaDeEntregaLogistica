package com.backend.SisLogisticaApplication.service;

import com.backend.SisLogisticaApplication.dtos.AgendamentoDTO;
import com.backend.SisLogisticaApplication.dtos.OcupacaoDTO;
import com.backend.SisLogisticaApplication.model.Agendamento;
import com.backend.SisLogisticaApplication.model.FaixaHoraria;

import com.backend.SisLogisticaApplication.model.TipoCaminhao;
import com.backend.SisLogisticaApplication.model.TipoPaletizacao;
import com.backend.SisLogisticaApplication.repository.AgendamentoRepository;
import com.backend.SisLogisticaApplication.repository.FaixaHorariaRepository;

import com.backend.SisLogisticaApplication.repository.TipoCaminhaoRepository;
import com.backend.SisLogisticaApplication.repository.TipoPaletizacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {
    private static final int LIMITE_PALLETS_DIA = 480;
    private static final int LIMITE_PALLETS_FAIXA = 120;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private FaixaHorariaRepository faixaHorariaRepository;

    @Autowired
    private TipoCaminhaoRepository tipoCaminhaoRepository;

    @Autowired
    private TipoPaletizacaoRepository tipoPaletizacaoRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    public AgendamentoDTO criarAgendamento(AgendamentoDTO dto) {

        FaixaHoraria faixaHoraria = faixaHorariaRepository.findById(dto.getFaixaHorariaId())
                .orElseThrow(() -> new RuntimeException("Faixa horária não encontrada"));

        TipoCaminhao tipoCaminhao = tipoCaminhaoRepository.findById(dto.getTipoCaminhaoId())
                .orElseThrow(() -> new RuntimeException("Tipo de caminhão não encontrado"));

        TipoPaletizacao tipoPaletizacao = tipoPaletizacaoRepository.findById(dto.getTipoPaletizacaoId())
                .orElseThrow(() -> new RuntimeException("Tipo de paletização não encontrado"));


        int quantidadeReal = dto.getQuantidadePallets();
        if (tipoPaletizacao.getDobraQuantidade()) {
            quantidadeReal *= 2;
        }


        OcupacaoDTO ocupacaoDia = calcularOcupacaoDia(dto.getData());
        OcupacaoDTO ocupacaoFaixa = calcularOcupacaoFaixaHoraria(dto.getData(), dto.getFaixaHorariaId());


        Agendamento agendamento = new Agendamento();
        agendamento.setData(dto.getData());
        agendamento.setFaixaHoraria(faixaHoraria);
        agendamento.setIdPedido(dto.getIdPedido());
        agendamento.setFornecedor(dto.getFornecedor());
        agendamento.setEmailFornecedor(dto.getEmailFornecedor());
        agendamento.setTipoCaminhao(tipoCaminhao);
        agendamento.setTipoPaletizacao(tipoPaletizacao);
        agendamento.setQuantidadePallets(dto.getQuantidadePallets());
        agendamento.setObservacao(dto.getObservacao());

        agendamento = agendamentoRepository.save(agendamento);


        try {
            emailService.enviarEmailAgendamento(agendamento);
            agendamento.setEmailEnviado(true);
            agendamentoRepository.save(agendamento);
        } catch (Exception e) {

            System.err.println("Erro ao enviar email: " + e.getMessage());
        }

        return convertToDTO(agendamento);
    }

    public List<AgendamentoDTO> listarAgendamentos() {
        return agendamentoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AgendamentoDTO> listarAgendamentosPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return agendamentoRepository.findByDataBetween(dataInicio, dataFim).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void cancelarAgendamento(Long id) {
        agendamentoRepository.deleteById(id);
    }

    public OcupacaoDTO calcularOcupacaoDia(LocalDate data) {
        Integer totalPallets = agendamentoRepository.calcularTotalPalletsPorDia(data);
        double porcentagem = (totalPallets * 100.0) / LIMITE_PALLETS_DIA;
        boolean excede = totalPallets > LIMITE_PALLETS_DIA;

        return new OcupacaoDTO(data, null, "Dia completo", totalPallets, porcentagem, excede);
    }

    public OcupacaoDTO calcularOcupacaoFaixaHoraria(LocalDate data, Long faixaHorariaId) {
        Integer totalPallets = agendamentoRepository.calcularTotalPalletsPorFaixaHoraria(data, faixaHorariaId);
        double porcentagem = (totalPallets * 100.0) / LIMITE_PALLETS_FAIXA;
        boolean excede = totalPallets > LIMITE_PALLETS_FAIXA;

        FaixaHoraria faixaHoraria = faixaHorariaRepository.findById(faixaHorariaId).orElse(null);
        String descricao = faixaHoraria != null ? faixaHoraria.getDescricao() : "Faixa não encontrada";

        return new OcupacaoDTO(data, faixaHorariaId, descricao, totalPallets, porcentagem, excede);
    }

    private AgendamentoDTO convertToDTO(Agendamento agendamento) {
        AgendamentoDTO dto = new AgendamentoDTO();
        dto.setId(agendamento.getId());
        dto.setData(agendamento.getData());
        dto.setFaixaHorariaId(agendamento.getFaixaHoraria().getId());
        dto.setIdPedido(agendamento.getIdPedido());
        dto.setFornecedor(agendamento.getFornecedor());
        dto.setEmailFornecedor(agendamento.getEmailFornecedor());
        dto.setTipoCaminhaoId(agendamento.getTipoCaminhao().getId());
        dto.setTipoPaletizacaoId(agendamento.getTipoPaletizacao().getId());
        dto.setQuantidadePallets(agendamento.getQuantidadePallets());
        dto.setObservacao(agendamento.getObservacao());


        dto.setFaixaHorariaDescricao(agendamento.getFaixaHoraria().getDescricao());
        dto.setTipoCaminhaoNome(agendamento.getTipoCaminhao().getNome());
        dto.setTipoPaletizacaoNome(agendamento.getTipoPaletizacao().getNome());


        OcupacaoDTO ocupacaoDia = calcularOcupacaoDia(agendamento.getData());
        OcupacaoDTO ocupacaoFaixa = calcularOcupacaoFaixaHoraria(agendamento.getData(), agendamento.getFaixaHoraria().getId());

        dto.setOcupacaoDia(ocupacaoDia.getPorcentagemOcupacao());
        dto.setOcupacaoFaixa(ocupacaoFaixa.getPorcentagemOcupacao());

        return dto;
    }
}
