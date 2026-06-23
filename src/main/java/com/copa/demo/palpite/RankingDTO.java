package com.copa.demo.palpite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RankingDTO {
    Long usuarioId;
    String nome;
    Long totalPontos;
}
