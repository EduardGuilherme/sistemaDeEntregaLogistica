package com.backend.SisLogisticaApplication.controller;

import com.backend.SisLogisticaApplication.dtos.AgendamentoDTO;
import com.backend.SisLogisticaApplication.dtos.OcupacaoDTO;
import com.backend.SisLogisticaApplication.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {
    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AgendamentoDTO> criarAgendamento(@RequestBody AgendamentoDTO dto) {
        AgendamentoDTO criado = agendamentoService.criarAgendamento(dto);
        return ResponseEntity.ok(criado);
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoDTO>> listarAgendamentos() {
        return ResponseEntity.ok(agendamentoService.listarAgendamentos());
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<AgendamentoDTO>> listarAgendamentosPorPeriodo(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim) {
        return ResponseEntity.ok(agendamentoService.listarAgendamentosPorPeriodo(inicio, fim));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> cancelarAgendamento(@PathVariable Long id) {
        agendamentoService.cancelarAgendamento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ocupacao/dia")
    public ResponseEntity<OcupacaoDTO> ocupacaoDia(@RequestParam LocalDate data) {
        return ResponseEntity.ok(agendamentoService.calcularOcupacaoDia(data));
    }

    @GetMapping("/ocupacao/faixa")
    public ResponseEntity<OcupacaoDTO> ocupacaoFaixa(@RequestParam LocalDate data,
                                                     @RequestParam Long faixaId) {
        return ResponseEntity.ok(agendamentoService.calcularOcupacaoFaixaHoraria(data, faixaId));
    }
}
