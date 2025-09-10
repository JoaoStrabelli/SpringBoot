package br.edu.utfpr.apiaula.controllers;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.apiaula.dtos.LembreteDTO;
import br.edu.utfpr.apiaula.models.Lembrete;
import br.edu.utfpr.apiaula.services.LembreteService;

@RestController
@RequestMapping("/person/{personId}/lembretes")
public class LembreteController {
    private LembreteService lembreteService;

    public LembreteController(LembreteService lembreteService) {
        this.lembreteService = lembreteService;
    }

    @PostMapping
    public Lembrete criar(@PathVariable String personId,
        @RequestBody LembreteDTO dto) {
        return lembreteService.salvar(personId, dto);
    }

    @GetMapping
    public Page<Lembrete> listar(
        @PathVariable String personId,
        @RequestParam(defaultValue = "0") int pagina,
        @RequestParam(defaultValue = "10") int tamanho) {
        return lembreteService.listarPorPessoa(personId, pagina, tamanho);
    }

}
