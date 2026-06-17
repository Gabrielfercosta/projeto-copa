package com.copa.demo.palpite;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "palpites")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "palpites")
public class Palpite{
    @Id    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long usuario;
    private Long jogoId;
    private Integer time1;
    private Integer time2;
    private Integer placarTime1;
    private Integer placarTime2;
    private Integer pontos;
    private LocalDateTime criadoEm;
}