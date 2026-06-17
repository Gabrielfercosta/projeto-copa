package com.copa.demo.palpite;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

public class PalpiteController{
    @Autowired
    private PalpiteRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    
    public java.util.List<Palpite> getAll(){
        return repository.findAll();
    }

    public Palpite savePalpite(@RequestBody PalpiteRequestDTO data){
        Palpite palpite = new Palpite();
        palpite.setUsuario(data.usuario());
        palpite.setJogoId(data.jogoId());
        palpite.setPlacarTime1(data.placarTime1());
        palpite.setPlacarTime2(data.placarTime2());
        palpite.setCriadoEm(LocalDateTime.now());
        return repository.save(palpite);
    }

}