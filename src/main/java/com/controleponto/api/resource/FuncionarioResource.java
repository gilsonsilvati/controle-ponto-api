package com.controleponto.api.resource;

import com.controleponto.api.domain.model.Funcionario;
import com.controleponto.api.domain.service.FuncionarioService;
import com.controleponto.api.event.RecursoCriadoEvent;
import com.controleponto.api.modelmapper.FuncionarioModel;
import com.controleponto.api.modelmapper.FuncionarioModelInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/funcionarios")
public class FuncionarioResource {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<FuncionarioModel> listar() {
        return funcionarioService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioModel> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(funcionarioService.porId(id));
    }

    @GetMapping("/email-cpf")
    public ResponseEntity<FuncionarioModel> buscarPorEmailOuCpf(@RequestParam(required = false) String email, @RequestParam(required = false) String cpf) {
        return ResponseEntity.ok(funcionarioService.porEmailOuCpf(email, cpf));
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody FuncionarioModelInput funcionarioModelInput, HttpServletResponse response) {
        Funcionario funcionario = funcionarioService.salvar(funcionarioModelInput);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, funcionario.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioModel> atualizar(@PathVariable Integer id, @RequestBody FuncionarioModel funcionarioModel) {
        return ResponseEntity.ok(funcionarioService.atualizar(id, funcionarioModel));
    }

    @PutMapping("/{id}/projeto")
    public ResponseEntity<FuncionarioModel> adicionarProjeto(@PathVariable Integer id, @RequestParam String nome) {
        return ResponseEntity.ok(funcionarioService.adicionarProjeto(id, nome));
    }

}
