package com.copa.demo.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<UsuarioResponseDTO> getAll(){
        return service.getAllUsuarios();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public UsuarioResponseDTO saveUsuario(@RequestBody UsuarioRequestDTO data){
        return service.createUsuario(data);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public UsuarioResponseDTO updateUsuario(@RequestBody UsuarioRequestDTO data, @PathVariable Long id){
        return service.updateUsuario(id, data);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id){
        service.deleteUsuario(id);
    }
}
