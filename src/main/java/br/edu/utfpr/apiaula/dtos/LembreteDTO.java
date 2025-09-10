package br.edu.utfpr.apiaula.dtos;

import java.time.LocalDateTime;

public record LembreteDTO(
    String mensagem,
    LocalDateTime dataHora) {
}
