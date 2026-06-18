package com.copa.demo.jogo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDTO
{
    Long id;
    String utcDate;
    String status;
    TeamDTO homeTeam;
    TeamDTO awayTeam;
    ScoreDTO score;
}
