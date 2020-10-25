package com.controleponto.api.resource;

import com.controleponto.api.domain.model.Projeto;
import com.controleponto.api.domain.service.ProjetoService;
import com.controleponto.api.event.RecursoCriadoEvent;
import com.controleponto.api.modelmapper.ProjetoModel;
import com.controleponto.api.modelmapper.ProjetoModelInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/projetos")
public class ProjetoResource {

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<ProjetoModel> listar() {
        return projetoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoModel> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(projetoService.porId(id));
    }

    @GetMapping("/nome")
    public ResponseEntity<ProjetoModel> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(projetoService.porNome(nome));
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody ProjetoModelInput projetoModelInput, HttpServletResponse response) {
        Projeto projeto = projetoService.salvar(projetoModelInput);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, projeto.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetoModel> atualizar(@PathVariable Integer id, @Valid @RequestBody ProjetoModelInput projetoModelInput) {
        return ResponseEntity.ok(projetoService.atualizar(id, projetoModelInput));
    }

    @PutMapping("/{id}/ativo")
    public ResponseEntity<Void> atualizarPropriedadeAtivo(@PathVariable Integer id, @RequestBody Boolean ativo) {
        projetoService.atualizarPropriedadeAtivo(id, ativo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
