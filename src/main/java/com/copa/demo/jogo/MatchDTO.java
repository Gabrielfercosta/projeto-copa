package com.copa.demo.jogo;

import java.time.LocalDateTime;

public record MatchDTO (
        Long id, LocalDateTime utcDate, String status, String homeTeam, String awayTeam, int score
){}
