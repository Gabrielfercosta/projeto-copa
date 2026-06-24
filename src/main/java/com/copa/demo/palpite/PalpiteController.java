package com.copa.demo.palpite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("palpite")
@CrossOrigin(origins = "https://projeto-copa-fawn.vercel.app", allowedHeaders = "*")
public class PalpiteController {

    @Autowired
    private PalpiteService service;

    @GetMapping
    public List<PalpiteResponseDTO> getAll() {
        return service.getAllPalpites();
    }

    @PostMapping
    public PalpiteResponseDTO savePalpite(@RequestBody PalpiteRequestDTO data) {
        return service.createPalpite(data);
    }

    @PutMapping("/{id}")
    public PalpiteResponseDTO updatePalpite(@RequestBody PalpiteRequestDTO data, @PathVariable Long id) {
        return service.updatePalpite(id, data);
    }

    @DeleteMapping("/{id}")
    public void deletePalpite(@PathVariable Long id) {
        service.deletePalpite(id);
    }

    @GetMapping("/ranking")
    public List<RankingDTO> getRanking(){
        return service.mostrarRanking();
    }
}
