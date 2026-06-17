package com.copa.demo.palpite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("palpite")
public class PalpiteController {

    @Autowired
    private PalpiteService service;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<PalpiteResponseDTO> getAll() {
        return service.getAllPalpites();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public Palpite savePalpite(@RequestBody PalpiteRequestDTO data) {
        return service.createPalpite(data);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public Palpite updatePalpite(@RequestBody PalpiteRequestDTO data, @PathVariable Long id) {
        return service.updatePalpite(id, data);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public void deletePalpite(@PathVariable Long id) {
        service.deletePalpite(id);
    }
}
