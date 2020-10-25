package com.controleponto.api.domain.service;

import com.controleponto.api.domain.exception.EntidadeNaoEncontradaException;
import com.controleponto.api.domain.exception.NegocioException;
import com.controleponto.api.domain.model.Funcionario;
import com.controleponto.api.domain.model.Projeto;
import com.controleponto.api.domain.repository.Funcionarios;
import com.controleponto.api.domain.repository.Projetos;
import com.controleponto.api.modelmapper.FuncionarioModel;
import com.controleponto.api.modelmapper.FuncionarioModelInput;
import com.controleponto.api.modelmapper.ProjetoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Funcionarios funcionarios;

    @Autowired
    private Projetos projetos;

    public List<FuncionarioModel> listar() {
        return toCollectionModel(funcionarios.findAll());
    }

    public FuncionarioModel porId(Integer id) {
        Funcionario funcionario = buscarPorId(id);
        return toModel(funcionario);
    }

    public FuncionarioModel porEmailOuCpf(String email, String cpf) {
        Funcionario funcionario = funcionarios.findByEmailIgnoreCaseOrCpf(email, cpf)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionário não localizado."));

        return toModel(funcionario);
    }

    public Funcionario salvar(FuncionarioModelInput funcionarioModelInput) {
        Optional<Funcionario> funcionarioExistente = buscarPorEmailOuCpf(funcionarioModelInput.getEmail(), funcionarioModelInput.getCpf());

        if (funcionarioExistente.isPresent())
            throw new NegocioException("Já existe um funcionário cadastrado com este e-mail ou cpf.");

        Funcionario funcionario = toEntity(funcionarioModelInput);
        funcionario = funcionarios.save(funcionario);

        return funcionario;
    }

    public FuncionarioModel atualizar(Integer id, FuncionarioModel funcionarioModel) {
        Funcionario funcionario = buscarPorId(id);
        Optional<Funcionario> funcionarioExistente = buscarPorEmailOuCpf(funcionarioModel.getEmail(), funcionarioModel.getCpf());

        if (funcionarioExistente.isPresent() && funcionarioExistente.get().getId() != funcionario.getId())
            throw new NegocioException("Já existe um funcionário cadastrado com este e-mail ou cpf.");

        BeanUtils.copyProperties(funcionarioModel, funcionario, "id");
        funcionarios.save(funcionario);

        return toModel(funcionario);
    }

    public FuncionarioModel adicionarProjeto(Integer id, String nome) {
        Projeto projeto = projetos.findByNomeIgnoreCaseAndAtivo(nome, true)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Projeto não localizado."));

        Funcionario funcionario = buscarPorId(id);

        boolean isExiste = funcionario.getProjetos().stream()
                .anyMatch(proj -> proj.equals(projeto));

        if (isExiste)
            throw new NegocioException("Funcionário " + funcionario.getNome() + " já está cadastrado neste projeto.");

        funcionario.getProjetos().add(projeto);
        funcionarios.save(funcionario);

        return toModel(funcionario);
    }

    private Funcionario buscarPorId(Integer id) {
        return funcionarios.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionário não localizado."));
    }

    private Optional<Funcionario> buscarPorEmailOuCpf(String email, String cpf) {
        return funcionarios.findByEmailIgnoreCaseOrCpf(email, cpf);
    }

    private List<FuncionarioModel> toCollectionModel(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .map(funcionario -> toModel(funcionario))
                .collect(Collectors.toList());
    }

    private FuncionarioModel toModel(Funcionario funcionario) {
        return modelMapper.map(funcionario, FuncionarioModel.class);
    }

    private Funcionario toEntity(FuncionarioModelInput funcionarioModelInput) {
        return modelMapper.map(funcionarioModelInput, Funcionario.class);
    }

    private ProjetoModel toModel(Projeto projeto) {
        return modelMapper.map(projeto, ProjetoModel.class);
    }

}
