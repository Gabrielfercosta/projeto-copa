package com.copa.demo.palpite;

import com.copa.demo.jogo.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PalpiteRepository extends JpaRepository<Palpite, Long>{
    List<Palpite> findByJogo(Jogo jogo);
}
