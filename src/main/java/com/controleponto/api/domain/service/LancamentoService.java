package com.controleponto.api.domain.service;

import com.controleponto.api.domain.exception.EntidadeNaoEncontradaException;
import com.controleponto.api.domain.exception.NegocioException;
import com.controleponto.api.domain.model.Funcionario;
import com.controleponto.api.domain.model.Lancamento;
import com.controleponto.api.domain.model.enums.Tipo;
import com.controleponto.api.domain.repository.Funcionarios;
import com.controleponto.api.domain.repository.Lancamentos;
import com.controleponto.api.modelmapper.LancamentoModel;
import com.controleponto.api.modelmapper.LancamentoModelInput;
import com.controleponto.api.modelmapper.RelatorioModel;
import com.controleponto.api.modelmapper.RelatorioModelInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class LancamentoService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Funcionarios funcionarios;

    @Autowired
    private Lancamentos lancamentos;

    public RelatorioModel relatorio(Integer id, RelatorioModelInput relatorioModelInput) {
        Funcionario funcionario = funcionarios.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionário não localizado."));

        List<Lancamento> list = lancamentos.findByFuncionarioAndCriacaoBetween(funcionario, relatorioModelInput.getInicio(), relatorioModelInput.getFim());

        // TODO: Realizar calculos das horas...

        return null;
    }

    public LancamentoModel registrar(LancamentoModelInput lancamentoModelInput) {
        verificarFimDeSemana();

        Funcionario funcionario = funcionarios.findByCpf(removerFormatacao(lancamentoModelInput.getCpf()))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionário não localizado."));

        List<Lancamento> lancamentosDoDia = lancamentos.findByFuncionarioAndCriacao(funcionario, LocalDate.now());
        Long quantidadeRegistroDia = lancamentosDoDia.stream().count();

        verificarHoraAlmoco(lancamentosDoDia, lancamentoModelInput.getHora());
        verificarRegistrosDia(quantidadeRegistroDia);

        lancamentoModelInput.setFuncionario(funcionario);
        lancamentoModelInput.setTipo(instanciarTipo(quantidadeRegistroDia));

        Lancamento lancamento = toEntity(lancamentoModelInput);
        lancamento = lancamentos.save(lancamento);

        return toModel(lancamento);
    }

    private Tipo instanciarTipo(Long quantidadeRegistroDia) {
        Tipo tipo = null;
        Integer quantidade = Math.toIntExact(quantidadeRegistroDia);

        switch (quantidade) {
            case 0:
                tipo = Tipo.INICIO_TRABALHO;
                break;
            case 1:
                tipo = Tipo.INICIO_ALMOCO;
                break;
            case 2:
                tipo = Tipo.TERMINO_ALMOCO;
                break;
            case 3:
                tipo = Tipo.TERMINO_TRABALHO;
                break;
        }

        return tipo;
    }

    private String removerFormatacao(String cpf) {
        return cpf.replaceAll("\\.|-|/", "");
    }

    private void verificarFimDeSemana() {
        LocalDate data = LocalDate.now();
        String diaDaSemana = getDiaDaSemana(data.getDayOfWeek());

        if (isFimDeSemana(data.getDayOfWeek()))
            throw new NegocioException(diaDaSemana + " não é permitido como dia de trabalho.");
    }

    private boolean isFimDeSemana(DayOfWeek diaDaSemana) {
        return diaDaSemana == DayOfWeek.SATURDAY || diaDaSemana == DayOfWeek.SUNDAY;
    }

    private String getDiaDaSemana(DayOfWeek diaDaSemana) {
        return diaDaSemana == DayOfWeek.SATURDAY ? "Sábado" : "Domingo";
    }

    private void verificarHoraAlmoco(List<Lancamento> lancamentosDoDia, LocalTime horaAlmoco) {
        List<Lancamento> lancamentos = lancamentosDoDia.stream()
                .filter(l -> l.getTipo().equals(Tipo.INICIO_ALMOCO))
                .collect(Collectors.toList());

        if (!lancamentos.isEmpty()) {
            long quantidadeMinutosAlmoco = ChronoUnit.MINUTES.between(lancamentos.get(0).getHora(), horaAlmoco);

            if (quantidadeMinutosAlmoco < 60)
                throw new NegocioException("Deve haver no mínimo 1h de almoço.");
        }
    }

    private void verificarRegistrosDia(Long quantidadeRegistroDia) {
        if (quantidadeRegistroDia == 4)
            throw new NegocioException("Apenas 4 horários podem ser registrados por dia.");
    }

    private List<LancamentoModel> toCollectionModel(List<Lancamento> lancamentos) {
        return lancamentos.stream()
                .map(lancamento -> toModel(lancamento))
                .collect(Collectors.toList());
    }

    private LancamentoModel toModel(Lancamento lancamento) {
        return modelMapper.map(lancamento, LancamentoModel.class);
    }

    private Lancamento toEntity(LancamentoModelInput lancamentoModelInput) {
        return modelMapper.map(lancamentoModelInput, Lancamento.class);
    }

}
