package com.copa.demo.jogo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "jogos")
@Table(name = "jogos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Jogo {
    @Id    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long apiId;
    private String time1;
    private String time2;
    private Integer placarTime1;
    private Integer placarTime2;
    private String dataHora;
    private String status;
    private String crestTime1;
    private String crestTime2;
}
