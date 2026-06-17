package com.copa.demo.palpite;

import com.copa.demo.usuario.Usuario;
import com.copa.demo.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PalpiteService {

    @Autowired
    private PalpiteRepository palpiteRepository;
    private UsuarioRepository usuarioRepository;

    public List<PalpiteResponseDTO> getAllPalpites() {
        return palpiteRepository.findAll().stream()
                .map(PalpiteResponseDTO::new)
                .toList();
    }

    public Palpite createPalpite(PalpiteRequestDTO data) {
        Usuario usuario = usuarioRepository.findById(data.usuario()).orElseThrow(() -> new RuntimeException("usuário não encontrado"));

        Palpite palpite = new Palpite();
        palpite.setUsuario(usuario);
        palpite.setJogoId(data.jogoId());
        palpite.setPlacarTime1(data.placarTime1());
        palpite.setPlacarTime2(data.placarTime2());
        palpite.setCriadoEm(LocalDateTime.now());
        return palpiteRepository.save(palpite);
    }

    public Palpite updatePalpite(Long id, PalpiteRequestDTO data) {
        Palpite palpite = palpiteRepository.findById(id).orElseThrow(() -> new RuntimeException("Palpite não encontrado"));

        palpite.setPlacarTime1(data.placarTime1());
        palpite.setPlacarTime2(data.placarTime2());
        return palpiteRepository.save(palpite);
    }

    public void deletePalpite(Long id) {
        palpiteRepository.deleteById(id);
    }
}
