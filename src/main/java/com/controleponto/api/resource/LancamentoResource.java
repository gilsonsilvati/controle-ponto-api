package com.controleponto.api.resource;

import com.controleponto.api.domain.service.LancamentoService;
import com.controleponto.api.modelmapper.LancamentoModel;
import com.controleponto.api.modelmapper.LancamentoModelInput;
import com.controleponto.api.modelmapper.RelatorioModel;
import com.controleponto.api.modelmapper.RelatorioModelInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoService lancamentoService;

    @PostMapping
    public ResponseEntity<LancamentoModel> registrar(@Valid @RequestBody LancamentoModelInput lancamentoModelInput) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoService.registrar(lancamentoModelInput));
    }

    @GetMapping("/funcionario/{id}/relatorio")
    public ResponseEntity<RelatorioModel> relatorio(@PathVariable Integer id, @Valid @RequestBody RelatorioModelInput relatorioModelInput) {
        return ResponseEntity.ok(lancamentoService.relatorio(id, relatorioModelInput));
    }

}
