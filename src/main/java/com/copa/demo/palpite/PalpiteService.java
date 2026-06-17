package com.copa.demo.palpite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PalpiteService {

    @Autowired
    private PalpiteRepository repository;

    public List<Palpite> getAllPalpites() {
        return repository.findAll();
    }

    public Palpite createPalpite(PalpiteRequestDTO data) {
        Palpite palpite = new Palpite();
        palpite.setUsuario(data.usuario());
        palpite.setJogoId(data.jogoId());
        palpite.setPlacarTime1(data.placarTime1());
        palpite.setPlacarTime2(data.placarTime2());
        palpite.setCriadoEm(LocalDateTime.now());
        return repository.save(palpite);
    }

    public Palpite updatePalpite(Long id, PalpiteRequestDTO data) {
        Palpite palpite = repository.findById(id).orElseThrow(() -> new RuntimeException("Palpite não encontrado"));

        palpite.setPlacarTime1(data.placarTime1());
        palpite.setPlacarTime2(data.placarTime2());
        return repository.save(palpite);
    }

    public void deletePalpite(Long id) {
        repository.deleteById(id);
    }
}
