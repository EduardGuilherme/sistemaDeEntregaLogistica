package com.backend.SisLogisticaApplication.service;

import com.backend.SisLogisticaApplication.model.Agendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarEmailAgendamento(Agendamento agendamento) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(agendamento.getEmailFornecedor());
        message.setSubject("Confirmação de Agendamento - Pedido #" + agendamento.getIdPedido());

        String corpo = construirCorpoEmail(agendamento);
        message.setText(corpo);

        mailSender.send(message);
    }

    private String construirCorpoEmail(Agendamento agendamento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return String.format(
                "Prezado fornecedor %s,\n\n" +
                        "Seu agendamento foi confirmado com sucesso!\n\n" +
                        "Detalhes do agendamento:\n" +
                        "• ID do Pedido: %d\n" +
                        "• Data: %s\n" +
                        "• Faixa Horária: %s\n" +
                        "• Tipo de Caminhão: %s\n" +
                        "• Tipo de Paletização: %s\n" +
                        "• Quantidade de Pallets: %d\n" +
                        "• Observações: %s\n\n" +
                        "Por favor, apresente-se no horário agendado.\n\n" +
                        "Atenciosamente,\n" +
                        "Sistema de Logística",
                agendamento.getFornecedor(),
                agendamento.getIdPedido(),
                agendamento.getData().format(formatter),
                agendamento.getFaixaHoraria().getDescricao(),
                agendamento.getTipoCaminhao().getNome(),
                agendamento.getTipoPaletizacao().getNome(),
                agendamento.getQuantidadePallets(),
                agendamento.getObservacao() != null ? agendamento.getObservacao() : "Nenhuma"
        );
    }
}
