package com.copa.demo.palpite;

import com.copa.demo.jogo.Jogo;
import com.copa.demo.jogo.JogoRepository;
import com.copa.demo.usuario.Usuario;
import com.copa.demo.usuario.UsuarioRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PalpiteService {

    @Autowired
    private PalpiteRepository palpiteRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JogoRepository jogoRepository;

    public List<PalpiteResponseDTO> getAllPalpites() {
        return palpiteRepository.findAll().stream()
                .map(PalpiteResponseDTO::new)
                .toList();
    }

    public PalpiteResponseDTO createPalpite(PalpiteRequestDTO data) {
        Usuario usuario = usuarioRepository.findById(data.usuario()).orElseThrow(() -> new RuntimeException("usuário não encontrado"));
        Jogo jogo = jogoRepository.findById(data.jogoId()).orElseThrow();

        if("TIMED".equals(jogo.getStatus())){
            Palpite palpite = new Palpite();
            palpite.setUsuario(usuario);
            palpite.setJogo(jogo);
            palpite.setPlacarTime1(data.placarTime1());
            palpite.setPlacarTime2(data.placarTime2());
            palpite.setCriadoEm(LocalDateTime.now());
            Palpite salvo = palpiteRepository.save(palpite);
            return new PalpiteResponseDTO(salvo);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Jogo já começou, não é possível palpitar");
    }

    public Palpite updatePalpite(Long id, PalpiteRequestDTO data) {
        Palpite palpite = palpiteRepository.findById(id).orElseThrow(() -> new RuntimeException("Palpite não encontrado"));

        palpite.setPlacarTime1(data.placarTime1());
        palpite.setPlacarTime2(data.placarTime2());
        return palpiteRepository.save(palpite);
    }

    public void deletePalpite(Long id) {
        palpiteRepository.deleteById(id);
    }

    public void calcularPontos(Palpite palpite){
        if(palpite.getJogo().getPlacarTime1() == null) return;
        if(palpite.getPlacarTime1().equals(palpite.getJogo().getPlacarTime1()) && palpite.getPlacarTime2().equals(palpite.getJogo().getPlacarTime2())){
            palpite.setPontos(3);
        }else if(palpite.getPlacarTime1() > palpite.getPlacarTime2() && palpite.getJogo().getPlacarTime1() > palpite.getJogo().getPlacarTime2()){
            palpite.setPontos(1);
        }else if(palpite.getPlacarTime2() > palpite.getPlacarTime1() && palpite.getJogo().getPlacarTime2() > palpite.getJogo().getPlacarTime1()) {
            palpite.setPontos(1);
        }else if(palpite.getPlacarTime1() - palpite.getPlacarTime2() == 0 && palpite.getJogo().getPlacarTime1() - palpite.getJogo().getPlacarTime2() == 0) {
            palpite.setPontos(1);
        }else{
            palpite.setPontos(0);
        }
        palpiteRepository.save(palpite);
    }
}
