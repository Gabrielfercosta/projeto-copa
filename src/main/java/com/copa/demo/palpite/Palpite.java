package com.copa.demo.palpite;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.copa.demo.jogo.Jogo;
import com.copa.demo.usuario.Usuario;
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
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "jogo_id")
    private Jogo jogo;
    private String time1;
    private String time2;
    private Integer placarTime1;
    private Integer placarTime2;
    private Integer pontos;
    private LocalDateTime criadoEm;
}