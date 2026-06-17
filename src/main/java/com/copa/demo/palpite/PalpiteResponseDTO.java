package com.copa.demo.palpite;

import java.time.LocalDateTime;

public record PalpiteResponseDTO(Long id, Long usuario, Long jogoId, Integer placarTime1, Integer placarTime2, Integer pontos, LocalDateTime criadoEm) {
    public PalpiteResponseDTO(Palpite palpite) {
        this(palpite.getId(), palpite.getUsuario().getId(), palpite.getJogoId(), palpite.getPlacarTime1(), palpite.getPlacarTime2(), palpite.getPontos(), palpite.getCriadoEm());
    }
}
