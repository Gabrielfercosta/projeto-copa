package com.copa.demo.jogo;

import com.copa.demo.palpite.Palpite;
import com.copa.demo.palpite.PalpiteRepository;
import com.copa.demo.palpite.PalpiteResponseDTO;
import com.copa.demo.palpite.PalpiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class JogoService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    JogoRepository repository;
    @Autowired
    PalpiteRepository palpiteRepository;
    @Autowired
    PalpiteService palpiteService;

    @Value("${football.api.url}")
    private String URL;

    @Value("${football.api.key}")
    private String key;

    private APIResponseDTO buscarJogos(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", key);
        headers.set("Accept", "application/json");
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        String endpoint = URL + "/competitions/WC/matches";

        ResponseEntity<APIResponseDTO> response = restTemplate.exchange(
                endpoint,
                HttpMethod.GET,
                entity,
                APIResponseDTO.class
        );

        return response.getBody();
    }

    @Scheduled(fixedRate = 60000)
    public void sincronizarJogos(){
        APIResponseDTO response = buscarJogos();
        for (MatchDTO match : response.getMatches()){
            Jogo jogo = repository.findByApiId(match.getId()).orElse(new Jogo());
            jogo.setApiId(match.getId());
            jogo.setTime1(match.getHomeTeam().getName());
            jogo.setTime2(match.getAwayTeam().getName());
            jogo.setPlacarTime1(match.getScore().getFullTime().getHome());
            jogo.setPlacarTime2(match.getScore().getFullTime().getAway());
            jogo.setDataHora(match.getUtcDate());
            jogo.setStatus(match.getStatus());
            jogo.setCrestTime1(match.getHomeTeam().getCrest());
            jogo.setCrestTime2(match.getAwayTeam().getCrest());
            repository.save(jogo);
            if("FINISHED".equals(jogo.getStatus())){
                List<Palpite> palpites = palpiteRepository.findByJogo(jogo);
                for (Palpite palpite : palpites) {
                    if(palpite.getPontos() == null){
                        palpiteService.calcularPontos(palpite);
                    }
                }
            }
        }
    }

    public List<Jogo> listarJogos(){
        return repository.findAll();
    }
}
