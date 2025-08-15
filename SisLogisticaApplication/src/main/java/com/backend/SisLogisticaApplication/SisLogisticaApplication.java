package com.backend.SisLogisticaApplication;

import com.backend.SisLogisticaApplication.model.FaixaHoraria;
import com.backend.SisLogisticaApplication.model.TipoCaminhao;
import com.backend.SisLogisticaApplication.model.TipoPaletizacao;
import com.backend.SisLogisticaApplication.repository.FaixaHorariaRepository;
import com.backend.SisLogisticaApplication.repository.TipoCaminhaoRepository;
import com.backend.SisLogisticaApplication.repository.TipoPaletizacaoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalTime;

@SpringBootApplication
public class SisLogisticaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SisLogisticaApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(TipoCaminhaoRepository tipoCaminhaoRepo,
							   TipoPaletizacaoRepository tipoPaletizacaoRepo,
							   FaixaHorariaRepository faixaHorarioRepo) {
		return args -> {
			if (tipoCaminhaoRepo.count() == 0) {
				tipoCaminhaoRepo.save(new TipoCaminhao("Truck","01"));
				tipoCaminhaoRepo.save(new TipoCaminhao("Carreta","02"));
				tipoCaminhaoRepo.save(new TipoCaminhao("Van","03"));
			}

			if (tipoPaletizacaoRepo.count() == 0) {
				tipoPaletizacaoRepo.save(new TipoPaletizacao("Paletizado",true));
				tipoPaletizacaoRepo.save(new TipoPaletizacao("Não Paletizado",false));
			}

			if (faixaHorarioRepo.count() == 0) {
				faixaHorarioRepo.save(new FaixaHoraria(LocalTime.of(8,0),LocalTime.of(10,0),"Manhã"));
				faixaHorarioRepo.save(new FaixaHoraria(LocalTime.of(10,0),LocalTime.of(12,0),"Manhã"));
				faixaHorarioRepo.save(new FaixaHoraria(LocalTime.of(13,0),LocalTime.of(15,0),"tarde"));
				faixaHorarioRepo.save(new FaixaHoraria(LocalTime.of(15,0),LocalTime.of(17,0),"tarde"));
			}
		};
	}
}
