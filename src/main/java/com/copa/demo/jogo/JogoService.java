package com.copa.demo.jogo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JogoService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${football.api.url}")
    private String URL;

    @Value("${football.api.key}")
    private String key;

    public String buscarJogos(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", key);
        headers.set("Accept", "application/json");
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        String endpoint = URL + "/competitions/WC/matches";

        ResponseEntity<String> response = restTemplate.exchange(
                endpoint,
                HttpMethod.GET,
                entity,
                String.class
        );

        return response.getBody();
    }
}
