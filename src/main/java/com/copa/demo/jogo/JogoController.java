package com.copa.demo.jogo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("jogo")
public class JogoController {

    @Autowired
    private JogoService service;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public APIResponseDTO buscarJogos() {
        return service.buscarJogos();
    }
}
