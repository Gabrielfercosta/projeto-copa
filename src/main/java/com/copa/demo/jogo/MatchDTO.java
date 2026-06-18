package com.copa.demo.jogo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDTO
{
    Long id;
    LocalDateTime utcDate;
    String status;
    TeamDTO homeTeam;
    TeamDTO awayTeam;
    ScoreDTO score;
}
