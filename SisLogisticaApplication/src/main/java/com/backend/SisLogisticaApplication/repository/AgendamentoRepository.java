package com.backend.SisLogisticaApplication.repository;

import com.backend.SisLogisticaApplication.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByDataBetween(LocalDate dataInicio, LocalDate dataFim);

    @Query("SELECT COALESCE(SUM(CASE WHEN a.tipoPaletizacao.dobraQuantidade = true THEN a.quantidadePallets * 2 ELSE a.quantidadePallets END), 0) " +
            "FROM Agendamento a WHERE a.data = :data")
    Integer calcularTotalPalletsPorDia(@Param("data") LocalDate data);

    @Query("SELECT COALESCE(SUM(CASE WHEN a.tipoPaletizacao.dobraQuantidade = true THEN a.quantidadePallets * 2 ELSE a.quantidadePallets END), 0) " +
            "FROM Agendamento a WHERE a.data = :data AND a.faixaHoraria.id = :faixaHorariaId")
    Integer calcularTotalPalletsPorFaixaHoraria(@Param("data") LocalDate data, @Param("faixaHorariaId") Long faixaHorariaId);
}
