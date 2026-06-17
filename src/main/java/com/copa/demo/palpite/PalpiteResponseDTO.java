package com.copa.demo.palpite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public record PalpiteResponseDTO(Long id, Long usuario, Long jogoId, Integer placarTime1, Integer placarTime2, Integer pontos, String criadoEm) {
    public PalpiteResponseDTO(Palpite palpite) {
        this(palpite.getId(), palpite.getUsuario(), palpite.getJogoId(), palpite.getPlacarTime1(), palpite.getPlacarTime2(), palpite.getPontos(), palpite.getCriadoEm());
    }
}
