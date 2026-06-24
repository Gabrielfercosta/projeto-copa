package com.copa.demo.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
@CrossOrigin(origins = "https://projeto-copa-fawn.vercel.app", allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<UsuarioResponseDTO> getAll(){
        return service.getAllUsuarios();
    }

    @PostMapping
    public UsuarioResponseDTO saveUsuario(@RequestBody UsuarioRequestDTO data){
        return service.createUsuario(data);
    }

    @PutMapping("/{id}")
    public UsuarioResponseDTO updateUsuario(@RequestBody UsuarioRequestDTO data, @PathVariable Long id){
        return service.updateUsuario(id, data);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id){
        service.deleteUsuario(id);
    }

    @PostMapping("/login")
    public UsuarioResponseDTO login(@RequestBody UsuarioRequestDTO data) {
        return service.login(data);
    }
}
