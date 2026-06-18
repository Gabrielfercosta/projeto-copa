package com.copa.demo.jogo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("jogo")
public class JogoController {

    @Autowired
    private JogoService service;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<Jogo> buscarJogos() {
        return service.listarJogos();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/sincronizar")
    public void sincronizar(){
        service.sincronizarJogos();
    }
}
