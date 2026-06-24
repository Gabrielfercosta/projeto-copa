package com.copa.demo.jogo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("jogo")
@CrossOrigin(origins = "https://projeto-copa-fawn.vercel.app", allowedHeaders = "*")
public class JogoController {

    @Autowired
    private JogoService service;

    @GetMapping
    public List<Jogo> buscarJogos() {
        return service.listarJogos();
    }

    @PostMapping("/sincronizar")
    public void sincronizar(){
        service.sincronizarJogos();
    }
}
