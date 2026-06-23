package com.copa.demo.palpite;

import com.copa.demo.jogo.Jogo;
import com.copa.demo.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PalpiteRepository extends JpaRepository<Palpite, Long>{
    List<Palpite> findByJogo(Jogo jogo);

    Optional<Palpite> findByUsuarioAndJogo(Usuario usuario, Jogo jogo);

    @Query("SELECT new com.copa.demo.palpite.RankingDTO(p.usuario.id, p.usuario.nome, CAST(COALESCE(SUM(p.pontos), 0) AS long)) FROM palpites p GROUP BY p.usuario.id, p.usuario.nome ORDER BY SUM(p.pontos) DESC")
    List<RankingDTO> getRanking();
}
